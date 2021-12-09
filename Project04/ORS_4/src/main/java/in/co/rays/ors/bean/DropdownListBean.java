package in.co.rays.ors.bean;

/**
 * DropdownList interface is implemented by Beans those are used to create drop
 * down list on HTML pages
 * 
 * @author Tanvi
 * @Version:(4.14.0)
 */
public interface DropdownListBean {

    /**
     * Returns key of list element
     * 
     * @return key
     */
    public String getKey();

    /**
     * Returns display text of list element
     * 
     * @return value
     */
    public String getValue();

}
