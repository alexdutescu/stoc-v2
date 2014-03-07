/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.alexdutescu.coduribare.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ro.alexdutescu.coduribare.model.StocTotal;


/**
 *
 * @author Alex Dutescu
 */
public class LazyStocDataModel extends LazyDataModel<StocTotal> {

    private final List<StocTotal> datasource;

    public LazyStocDataModel(List<StocTotal> datasource) {
        this.datasource = datasource;
    }

    @Override
    public StocTotal getRowData(String rowKey) {
        for (StocTotal stoc : datasource) {
            if (stoc.getNume().equals(rowKey)) {
                return stoc;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(StocTotal stoc) {
        return stoc.getNume();
    }

    @Override
    public List<StocTotal> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<StocTotal> data = new ArrayList<StocTotal>();
        
        //filter  
        for (StocTotal stoc : datasource) {
            boolean match = true;

            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    String fieldValue = String.valueOf(stoc.getClass().getField(filterProperty).get(stoc));

                    if (filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    } else {
                        match = false;
                        break;
                    }
                } catch (Exception e) {
                    match = false;
                }
            }

            if (match) {
                data.add(stoc);
            }
        }

        //sort  
        if (sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        //rowCount  
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate  
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
