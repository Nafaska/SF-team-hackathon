package com.ascendix.services;

import com.Settings;
import com.ascendix.models.VSTSEntity;
import com.ascendix.models.VSTSEntityResponse;
import com.ascendix.models.VSTSItem;
import com.ascendix.models.VSTSItemsResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VSTSService {
    public List<VSTSEntity> getProjects() {
        return getEntity("/_apis/projects").getValue();
    }

    public List<VSTSEntity> getQueries(String projectId) {
        return getEntity("/" + projectId + "/_apis/wit/queries").getValue();
    }

    public List<VSTSItem> getItemsByQueryId(String queryId) {
        List<String> fields = new ArrayList<>();
        fields.add("System.Title");
        fields.add("System.WorkItemType");
        fields.add("System.WorkItemType");
        fields.add("Microsoft.VSTS.Common.ClosedDate");
        fields.add("Microsoft.VSTS.Scheduling.CompletedWork");
        fields.add("System.State");

        Set<String> itemsId = new HashSet<>();
        List<VSTSEntity> entities = getEntity("/_apis/wit/wiql/" + queryId).getWorkItems();
        if (entities == null || entities.isEmpty()) {
            return new ArrayList<>();
        }

        entities.forEach(item -> {
            itemsId.add(item.getId());
        });
        //TODO: add limit of Items
        String fieldsString = StringUtils.arrayToCommaDelimitedString(fields.toArray());
        String idsString = StringUtils.arrayToCommaDelimitedString(itemsId.toArray());
        String urlSuffix = "/_apis/wit/workitems?api-version=5.0&fields=" + fieldsString + "&ids=" + idsString;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new UserAuthService().getBasicAuthHeaders(Settings.USERNAME, Settings.TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<VSTSItemsResponse> response = restTemplate.exchange(Settings.TFS_URL + urlSuffix, HttpMethod.GET, entity, VSTSItemsResponse.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return getFilteredItems(response.getBody().getValue());
        }

        return null;
    }

    private List<VSTSItem> getFilteredItems(List<VSTSItem> items) {
        List<VSTSItem> result = new ArrayList<>();
        if (items == null || items.isEmpty()) {
            return new ArrayList<>();
        }
        Set<String> supportedTypes = new HashSet<>();
        supportedTypes.add("Task");
        supportedTypes.add("Bug");
        supportedTypes.add("User Story");
        items.forEach(item -> {
            if (supportedTypes.contains(item.getType()) && "Closed".equals(item.getState())) {
                result.add(item);
            }
        });

        return items;
    }


    private VSTSEntityResponse getEntity(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new UserAuthService().getBasicAuthHeaders(Settings.USERNAME, Settings.TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<VSTSEntityResponse> response = restTemplate.exchange(Settings.TFS_URL + url, HttpMethod.GET, entity, VSTSEntityResponse.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        }
        return null;

    }
}
