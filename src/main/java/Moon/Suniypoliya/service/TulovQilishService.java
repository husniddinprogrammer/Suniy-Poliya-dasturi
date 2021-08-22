package Moon.Suniypoliya.service;

import Moon.Suniypoliya.entity.TulovQilish;

import java.util.List;
import java.util.Optional;

public interface TulovQilishService  extends PublicService<TulovQilish,Long>{
    public List<TulovQilish> getAllByBuyurtmaId(Long id);
}
