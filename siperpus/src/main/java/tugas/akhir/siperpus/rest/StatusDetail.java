package tugas.akhir.siperpus.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown=true)
public class StatusDetail {

    @JsonProperty("status")
    public String status;

    @JsonProperty("message")
    public String message;

    @JsonProperty("result")
	private UserDetail user;
	
	/**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @return the listUser
     */
    // public List<UserDetail> getListUser() {
    //     return listUser;
    // }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @param listUser the listUser to set
     */
    public void setListUser(UserDetail user) {
        this.user = user;
    }
    
    /**
     * @return the user
     */
    /**
     * @return the user
     */
    public UserDetail getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(UserDetail user) {
        this.user = user;
    }
}
