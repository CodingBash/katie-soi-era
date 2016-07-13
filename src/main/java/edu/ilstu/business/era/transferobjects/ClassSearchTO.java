
package edu.ilstu.business.era.transferobjects;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ClassSearchTO {

    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private String previous;
    @SerializedName("classList")
    @Expose
    private List<ClassListTO> classList = new ArrayList<ClassListTO>();

    /**
     * 
     * @return
     *     The next
     */
    public String getNext() {
        return next;
    }

    /**
     * 
     * @param next
     *     The next
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * 
     * @return
     *     The previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * 
     * @param previous
     *     The previous
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * 
     * @return
     *     The classList
     */
    public List<ClassListTO> getClassList() {
        return classList;
    }

    /**
     * 
     * @param classList
     *     The classList
     */
    public void setClassList(List<ClassListTO> classList) {
        this.classList = classList;
    }

}
