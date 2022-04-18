package bootcamp.microservices.app.companymovements.documents;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document
public class CompanyMovement implements Serializable {

	private static final long serialVersionUID = 1404357914704147098L;

	private String id;

	private OperationType operationType;
	
	private String idOriginMovement;
	
	private String idDestinyMovement;

	private Integer movementType;

	private Double amount;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date operationDate;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createDate;

	private String createUser;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date modifyDate;

	private String modifyUser;

}
