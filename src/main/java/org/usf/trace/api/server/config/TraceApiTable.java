package org.usf.trace.api.server.config;

import java.util.function.Function;

import org.usf.jquery.web.ColumnDecorator;
import org.usf.jquery.web.TableDecorator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TraceApiTable implements TableDecorator {

    INC_REQ("E_IN_REQ", DataConstants::incReqColumns);

    private final String tableName;
    private final Function<TraceApiColumn, String> columnMap;

    @Override
    public String identity() {
        return name();
    }

    @Override
    public String columnName(ColumnDecorator desc) {
        return columnMap.apply((TraceApiColumn) desc);
    }

    @Override
    public String sql() {
        return tableName;
    }
}
