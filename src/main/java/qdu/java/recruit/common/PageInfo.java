package qdu.java.recruit.common;


import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {
    private Integer pageNum;
    private long totalSize;
    private List<T> pageData;
}
