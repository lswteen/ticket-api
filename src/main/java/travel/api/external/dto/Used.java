package travel.api.external.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by we on 2017. 3. 15..
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Used {
    private Long success;
    private Long failure;
    private List<UsedResults> results;

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }

    public Long getFailure() {
        return failure;
    }

    public void setFailure(Long failure) {
        this.failure = failure;
    }

    public List<UsedResults> getResults() {
        return results;
    }

    public void setResults(List<UsedResults> results)
    {this.results = results;}
}
