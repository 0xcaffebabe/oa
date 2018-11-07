package wang.ismy.oa.dto;

import lombok.Data;

@Data
public class Page {

    private Integer pageNumber;

    private Integer length;

    public Page(Integer pageNumber, Integer length) {
        this.pageNumber = pageNumber;
        this.length = length;
    }

    public Page() {
    }
}
