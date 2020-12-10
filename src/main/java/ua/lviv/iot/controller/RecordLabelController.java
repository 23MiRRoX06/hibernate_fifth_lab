package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.RecordLabelServiceImpl;
import ua.lviv.iot.model.RecordLabel;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordLabelController {
  @Getter
  private static final RecordLabelController INSTANCE = new RecordLabelController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private RecordLabelServiceImpl recordLabelService = RecordLabelServiceImpl.getINSTANCE();

  public void createRecordLabel() {
    RecordLabel recordLabel = createRecordLabelInstanceFromUserInputs();
    recordLabelService.create(recordLabel);
    System.out.println("There are 1 row created");
  }

  public void updateRecordLabel() {
    RecordLabel recordLabel = createRecordLabelInstanceFromUserInputs();
    System.out.println("Input id(id) for RecordLabel:  ");
    recordLabel.setId(input.nextInt());
    recordLabelService.update(recordLabel);
    System.out.println("There are 1 row updated");
  }

  public void deleteRecordLabel() {
    System.out.println("Input id(id) for RecordLabel");
    Integer deleteId = input.nextInt();
    int count = recordLabelService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromRecordLabel() {
    System.out.println("\nTable: RecordLabel");
    List<RecordLabel> recordLabels = recordLabelService.findAll();
    for (RecordLabel recordLabel : recordLabels) {
      System.out.println(recordLabel);
    }
  }

  public void selectFromRecordLabelById() {
    System.out.println("Input id(id) for RecordLabel");
    Integer selectId = input.nextInt();
    RecordLabel recordLabel = recordLabelService.findById(selectId);
    System.out.println(recordLabel);
  }

  private RecordLabel createRecordLabelInstanceFromUserInputs() {
    System.out.println("Input name(name) for RecordLabel:  ");
    String name = input.nextLine();
    return new RecordLabel().setName(name);
  }
}
