/*
 * This work is dual-licensed
 * - under the Apache Software License 2.0 (the "ASL")
 * - under the jOOQ License and Maintenance Agreement (the "jOOQ License")
 * =============================================================================
 * You may choose which license applies to you:
 *
 * - If you're using this work with Open Source databases, you may choose
 *   either ASL or jOOQ License.
 * - If you're using this work with at least one commercial database, you must
 *   choose jOOQ License
 *
 * For more information, please visit http://www.jooq.org/licenses
 *
 * Apache Software License 2.0:
 * -----------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * jOOQ License and Maintenance Agreement:
 * -----------------------------------------------------------------------------
 * Data Geekery grants the Customer the non-exclusive, timely limited and
 * non-transferable license to install and use the Software under the terms of
 * the jOOQ License and Maintenance Agreement.
 *
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ License
 * and Maintenance Agreement for more details: http://www.jooq.org/licensing
 */
package org.jooq.mcve.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.jooq.mcve.Routines;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import org.jooq.mcve.udt.records.MyTypeRecord;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;
import org.jooq.mcve.DefaultSchema;

public class MCVEIT {

    Connection connection;
    

    @Before
    public void setup() throws Exception {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:44444/postgres", "postgres", "");
        connection.setSchema("mcve");
    }

    @After
    public void after() throws Exception {
        connection.close();
        connection = null;
    }

    @Test
    public void mcveTest() {
		
        Configuration configuration = new DefaultConfiguration().
			set(connection).
			set(SQLDialect.POSTGRES);
			/*set(new Settings().
				withRenderMapping(new RenderMapping().
					withSchemata(new MappedSchema().
						withInput(DefaultSchema.DEFAULT_SCHEMA.getName()).
						withOutput("mcve"))));*/
			
		String[] inArray = new String[] {"Hi", "planet"};
		
		MyTypeRecord[] inArrayUdt = new MyTypeRecord[] {
			new MyTypeRecord("Hello", 1), 
			new MyTypeRecord("world", 2)
		};
		
        Integer result = Routines.fTestFunc(configuration, inArray, inArrayUdt, new MyTypeRecord("Text", 1));
        assertEquals(1, (int) result);
    }
}
