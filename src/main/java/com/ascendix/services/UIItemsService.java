package com.ascendix.services;

import com.ascendix.models.Option;
import com.ascendix.models.TimeFoxTask;
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
            result.add(new UIItem(item.getCompletedHours(), item.getTitle(), item.getClosedDate()));
        });

        return result;
    }


}
