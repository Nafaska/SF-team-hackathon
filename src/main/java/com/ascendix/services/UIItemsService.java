package com.ascendix.services;

import com.ascendix.models.UIItem;
import com.ascendix.models.VSTSItem;

import java.util.ArrayList;
import java.util.List;

public class UIItemsService {
    public List<UIItem> getItemsByQueryId(String queryId) {
        List<UIItem> result = new ArrayList<>();
        List<VSTSItem> items = new VSTSService().getItemsByQueryId(queryId);
        if (items == null || items.isEmpty()) {
            return new ArrayList<>();
        }
        items.forEach(item -> {
            result.add( buildUIItem(item));
        });

        return result;
    }

    private UIItem buildUIItem(VSTSItem item) {
        UIItem result = new UIItem();
        result.setSpentHours(item.getCompletedHours());
        result.setClosedDate(item.getClosedDate());
        result.setDescription(item.getTitle());
        return result;
    }


}
