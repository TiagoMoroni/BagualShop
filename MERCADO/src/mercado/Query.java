/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

/**
 *
 * @author 05200244
 */
public class Query {

    private String query = null;
    private Object[] parameters = null;

    public Query(String query) {
        this.query = query;
    }

    public Query(String query, Object... parameters) {
        this.query = query;
        this.parameters = parameters;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setParameters(Object... parameters) {
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void execute(Database database) {
        database.query(query, parameters);
    } 
}
