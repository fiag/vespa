package com.yahoo.vespa.hadoop.util;

import com.yahoo.vespa.hadoop.mapreduce.util.TupleTools;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.apache.pig.impl.logicalLayer.schema.Schema;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TupleToolsTest {

    @Test
    public void requireThatTupleToStringHandlesSimpleTypes() throws IOException {
        Schema schema = new Schema();
        Tuple tuple = TupleFactory.getInstance().newTuple();

        addToTuple("id", DataType.CHARARRAY, "123", schema, tuple);
        addToTuple("rank", DataType.INTEGER, 1, schema, tuple);

        String template = "Id is <id> and rank is <rank>";
        String result = TupleTools.toString(schema, tuple, template);

        assertEquals("Id is 123 and rank is 1", result);
    }


    private void addToTuple(String alias, byte type, Object value, Schema schema, Tuple tuple) {
        schema.add(new Schema.FieldSchema(alias, type));
        tuple.append(value);
    }

}
