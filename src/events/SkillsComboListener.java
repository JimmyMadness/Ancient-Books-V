package events;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import model.character.SkillsType;

public class SkillsComboListener implements	ChangeListener<SkillsType>{
	
	private ComboBox<SkillsType> combo1;
	private ComboBox<SkillsType> combo2;
	private ComboBox<SkillsType> combo3;
	private ComboBox<SkillsType> combo4;
	private ComboBox<SkillsType> combo5;
	private ComboBox<SkillsType> combo6;
	private ObservableList<SkillsType> list1;
	private ObservableList<SkillsType> list2;
	private ObservableList<SkillsType> list3;
	private ObservableList<SkillsType> list4;
	private ObservableList<SkillsType> list5;
	private ObservableList<SkillsType> list6;

	
	
	//TODO: change parameters to lists

	public SkillsComboListener(ComboBox<SkillsType> Combo1, ComboBox<SkillsType> Combo2,
			ComboBox<SkillsType> Combo3, ComboBox<SkillsType> Combo4, ComboBox<SkillsType> Combo5,
			ComboBox<SkillsType> Combo6, ObservableList<SkillsType> List1, ObservableList<SkillsType> List2,
			ObservableList<SkillsType> List3, ObservableList<SkillsType> List4,
			ObservableList<SkillsType> List5, ObservableList<SkillsType> List6) {
		super();
		this.combo1 = Combo1;
		this.combo2 = Combo2;
		this.combo3 = Combo3;
		this.combo4 = Combo4;
		this.combo5 = Combo5;
		this.combo6 = Combo6;
		this.list1 = List1;
		this.list2 = List2;
		this.list3 = List3;
		this.list4 = List4;
		this.list5 = List5;
		this.list6 = List6;
	}



	@Override
	public void changed(ObservableValue<? extends SkillsType> observable, SkillsType oldValue, SkillsType newValue) {
		combo1.getSelectionModel().selectedItemProperty().removeListener(this);
		combo2.getSelectionModel().selectedItemProperty().removeListener(this);
		combo3.getSelectionModel().selectedItemProperty().removeListener(this);
		combo4.getSelectionModel().selectedItemProperty().removeListener(this);
		combo5.getSelectionModel().selectedItemProperty().removeListener(this);
		combo6.getSelectionModel().selectedItemProperty().removeListener(this);
	
		//
		// CODE GOES HERE
		//
		
		ReadOnlyObjectProperty<SkillsType> observableProperty = (ReadOnlyObjectProperty<SkillsType>)observable;
		SingleSelectionModel<SkillsType> currentCombo = (SingleSelectionModel<SkillsType>)observableProperty.getBean();
		if (combo1.getSelectionModel().equals(currentCombo)) {
			if (!newValue.equals(SkillsType.NONE)) {
				list2.remove(newValue);
				list3.remove(newValue);
				list4.remove(newValue);
				list5.remove(newValue);
				list6.remove(newValue);
			}
			if(!oldValue.equals(SkillsType.NONE)) {
				list2.add(oldValue);
				list3.add(oldValue);
				list4.add(oldValue);
				list5.add(oldValue);
				list6.add(oldValue);
			}
			FXCollections.sort(list2);
			FXCollections.sort(list3);
			FXCollections.sort(list4);
			FXCollections.sort(list5);
			FXCollections.sort(list6);
		}
		if (combo2.getSelectionModel().equals(currentCombo)) {
			if (!newValue.equals(SkillsType.NONE)) {
				list1.remove(newValue);
				list3.remove(newValue);
				list4.remove(newValue);
				list5.remove(newValue);
				list6.remove(newValue);
			}
			if(!oldValue.equals(SkillsType.NONE)) {
				list1.add(oldValue);
				list3.add(oldValue);
				list4.add(oldValue);
				list5.add(oldValue);
				list6.add(oldValue);
			}
			FXCollections.sort(list1);
			FXCollections.sort(list3);
			FXCollections.sort(list4);
			FXCollections.sort(list5);
			FXCollections.sort(list6);
		}
		if (combo3.getSelectionModel().equals(currentCombo)) {
			if (!newValue.equals(SkillsType.NONE)) {
				list1.remove(newValue);
				list2.remove(newValue);
				list4.remove(newValue);
				list5.remove(newValue);
				list6.remove(newValue);
			}
			if(!oldValue.equals(SkillsType.NONE)) {
				list1.add(oldValue);
				list2.add(oldValue);
				list4.add(oldValue);
				list5.add(oldValue);
				list6.add(oldValue);
			}
			FXCollections.sort(list1);
			FXCollections.sort(list2);
			FXCollections.sort(list4);
			FXCollections.sort(list5);
			FXCollections.sort(list6);
		}
		if (combo4.getSelectionModel().equals(currentCombo)) {
			if (!newValue.equals(SkillsType.NONE)) {
				list1.remove(newValue);
				list2.remove(newValue);
				list3.remove(newValue);
				list5.remove(newValue);
				list6.remove(newValue);
			}
			if(!oldValue.equals(SkillsType.NONE)) {
				list1.add(oldValue);
				list2.add(oldValue);
				list3.add(oldValue);
				list5.add(oldValue);
				list6.add(oldValue);
			}
			FXCollections.sort(list1);
			FXCollections.sort(list2);
			FXCollections.sort(list3);
			FXCollections.sort(list5);
			FXCollections.sort(list6);
		}
		if (combo5.getSelectionModel().equals(currentCombo)) {
			if (!newValue.equals(SkillsType.NONE)) {
				list1.remove(newValue);
				list2.remove(newValue);
				list3.remove(newValue);
				list4.remove(newValue);
				list6.remove(newValue);
			}
			if(!oldValue.equals(SkillsType.NONE)) {
				list1.add(oldValue);
				list2.add(oldValue);
				list3.add(oldValue);
				list4.add(oldValue);
				list6.add(oldValue);
			}
			FXCollections.sort(list1);
			FXCollections.sort(list2);
			FXCollections.sort(list3);
			FXCollections.sort(list4);
			FXCollections.sort(list6);
		}
		if (combo6.getSelectionModel().equals(currentCombo)) {
			if (!newValue.equals(SkillsType.NONE)) {
				list1.remove(newValue);
				list2.remove(newValue);
				list3.remove(newValue);
				list4.remove(newValue);
				list5.remove(newValue);
			}
			if(!oldValue.equals(SkillsType.NONE)) {
				list1.add(oldValue);
				list2.add(oldValue);
				list3.add(oldValue);
				list4.add(oldValue);
				list5.add(oldValue);
			}
			FXCollections.sort(list1);
			FXCollections.sort(list2);
			FXCollections.sort(list3);
			FXCollections.sort(list4);
			FXCollections.sort(list5);
		}
		
		combo1.getSelectionModel().selectedItemProperty().addListener(this);
		combo2.getSelectionModel().selectedItemProperty().addListener(this);
		combo3.getSelectionModel().selectedItemProperty().addListener(this);
		combo4.getSelectionModel().selectedItemProperty().addListener(this);
		combo5.getSelectionModel().selectedItemProperty().addListener(this);
		combo6.getSelectionModel().selectedItemProperty().addListener(this);
		
	}
	

	






	/*
	 * 	
		if (!newValue.equals(SkillsType.NONE)) {
			otherList1.remove(newValue);
			otherList2.remove(newValue);
			otherList3.remove(newValue);
			otherList4.remove(newValue);
			otherList5.remove(newValue);
		}
		if(!oldValue.equals(SkillsType.NONE)) {
			otherList1.add(oldValue);
			otherList2.add(oldValue);
			otherList3.add(oldValue);
			otherList4.add(oldValue);
			otherList5.add(oldValue);
		}
		FXCollections.sort(otherList1);
		FXCollections.sort(otherList2);
		FXCollections.sort(otherList3);
		FXCollections.sort(otherList4);
		FXCollections.sort(otherList5);
		
	 */
	
}
