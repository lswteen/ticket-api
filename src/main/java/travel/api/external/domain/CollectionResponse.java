package travel.api.external.domain;

import java.util.List;

/**
 * Created by we on 2017. 2. 24..
 */
public class CollectionResponse <T> extends AbstractObject{
    private List<T> list;

    public CollectionResponse(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }

}
