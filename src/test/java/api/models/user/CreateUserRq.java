package api.models.user;

import api.models.base.Requestable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRq implements Requestable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("birthdate")
    @Expose
    private String birthdate;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
}
