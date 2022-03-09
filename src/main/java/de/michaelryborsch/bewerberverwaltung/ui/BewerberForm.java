package de.michaelryborsch.bewerberverwaltung.ui;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import de.michaelryborsch.bewerberverwaltung.backend.BewerberService;
import de.michaelryborsch.bewerberverwaltung.backend.model.Bewerber;


public class BewerberForm extends FormLayout {

    private Bewerber bewerber;
    private BewerberService service;
    private VerwaltungUI ui;

    private TextField vorname =new TextField("Vorname");
    private TextField name =new TextField("Nachname");
    private TextField email =new TextField("E-Mail");
    private IntegerField telefonNr =new IntegerField("Telefonnummer");
    private ComboBox<Bewerber.Arbeitszeit> arbeitszeit =new ComboBox("Arbeitszeit");
    private TextField gewuenschtePosition =new TextField("Gewünschte Position");
    private NumberField gehaltswunsch =new NumberField("Gewünschtes Gehalt");
    private ComboBox<Bewerber.Bewerberstatus> bewerberStatus =new ComboBox<>("Bewerber Status");
    private Button btn_save = new Button("Speichern");
    private Button btn_delete = new Button("Löschen");
    private Button btn_close = new Button ("Abbrechen");

    private Binder<Bewerber> binder = new BeanValidationBinder<>(Bewerber.class);

    public BewerberForm()
    {


        bewerberStatus.setItems(Bewerber.Bewerberstatus.values());
        bewerberStatus.setItemLabelGenerator(Bewerber.Bewerberstatus::getName);
        arbeitszeit.setItems(Bewerber.Arbeitszeit.values());
        arbeitszeit.setItemLabelGenerator(Bewerber.Arbeitszeit::getName);

        binder.bindInstanceFields(this);
        setSizeUndefined();
        HorizontalLayout buttons= new HorizontalLayout(btn_save,btn_delete);
        add(vorname, name, email, telefonNr, arbeitszeit, gewuenschtePosition, gehaltswunsch, bewerberStatus,btn_save,btn_delete);
        btn_save.addClickListener(e -> validateAndSave());
        btn_close.addClickListener(e-> fireEvent(new CloseEvent(this)));
        btn_delete.addClickListener(e->fireEvent(new DeleteEvent(this,bewerber)));


        binder.addStatusChangeListener(evt->btn_save.setAutofocus(binder.isValid()));


    }

    private void validateAndSave()
    {
        try {
            binder.writeBean(bewerber);
            fireEvent(new SaveEvent(this,bewerber));
        } catch (ValidationException e) {
            e.printStackTrace();
        }

    }

    public void setBewerber(Bewerber bewerber)
    {

        this.bewerber=bewerber;
        binder.readBean(bewerber);

    }





    public static abstract class BewerberFormEvent extends ComponentEvent<BewerberForm> {
        private Bewerber bewerber;

        protected BewerberFormEvent(BewerberForm source, Bewerber bewerber) {
            super(source, false);
            this.bewerber = bewerber;
        }

        public Bewerber getBewerber() {
            return bewerber;
        }
    }

    public static class SaveEvent extends BewerberFormEvent {
        SaveEvent(BewerberForm source, Bewerber bewerber) {
            super(source, bewerber);
        }
    }

    public static class DeleteEvent extends BewerberFormEvent {
        DeleteEvent(BewerberForm source, Bewerber bewerber) {
            super(source, bewerber);
        }

    }

    public static class CloseEvent extends BewerberFormEvent {
        CloseEvent(BewerberForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}
