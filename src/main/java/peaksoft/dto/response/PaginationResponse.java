package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PaginationResponse<T> {
    private List<T> t;
    private int currentPage;
    private int pageSize;
}
