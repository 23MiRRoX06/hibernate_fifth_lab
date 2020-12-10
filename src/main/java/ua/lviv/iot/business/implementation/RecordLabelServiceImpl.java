package ua.lviv.iot.business.implementation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.RecordLabelService;
import ua.lviv.iot.data_access.implementation.RecordLabelDaoImpl;
import ua.lviv.iot.model.RecordLabel;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordLabelServiceImpl implements RecordLabelService {
  @Getter
  private static final RecordLabelServiceImpl INSTANCE = new RecordLabelServiceImpl();
  private RecordLabelDaoImpl recordLabelDao = RecordLabelDaoImpl.getINSTANCE();

  @Override
  public List<RecordLabel> findAll() {
    return recordLabelDao.findAll();
  }

  @Override
  public RecordLabel findById(Integer id) {
    return recordLabelDao.findById(id);
  }

  @Override
  public void create(RecordLabel recordLabel) {
    recordLabelDao.create(recordLabel);
  }

  @Override
  public void update(RecordLabel recordLabel) {
    recordLabelDao.update(recordLabel);
  }

  @Override
  public int delete(Integer id) {
    return recordLabelDao.delete(id);
  }
}
