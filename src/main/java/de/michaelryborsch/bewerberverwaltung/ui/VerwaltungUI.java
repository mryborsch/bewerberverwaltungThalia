package de.michaelryborsch.bewerberverwaltung.ui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.michaelryborsch.bewerberverwaltung.backend.BewerberService;
import de.michaelryborsch.bewerberverwaltung.backend.model.Bewerber;


@PageTitle("Verwaltung")
@Route(value="")
public class VerwaltungUI extends VerticalLayout {

    private BewerberService service;
    private BewerberForm form;
    private Button btn_delete;
    private Grid <Bewerber> grid = new Grid(Bewerber.class);


    public VerwaltungUI(BewerberService service)
    {
        this.service=service;
        addClassName("list-view");
        setSizeFull();
        form= new BewerberForm();
        form.addListener(BewerberForm.SaveEvent.class,this::saveBewerber);
        form.addListener(BewerberForm.DeleteEvent.class,this::deleteBewerber);
        form.addListener(BewerberForm.CloseEvent.class,e->closeEditor());


        configureGrid();
        Div content = new Div(grid,form);
        add(getToolBar(),content);

        updateList();
        closeEditor();
    }

    private void deleteBewerber(BewerberForm.DeleteEvent evt){
        System.out.println(evt.getBewerber().toString());

        service.deleteBewerber(evt.getBewerber().getId());
        updateList();
        closeEditor();

    }

    private void saveBewerber(BewerberForm.SaveEvent evt) {
        Bewerber bewerber = evt.getBewerber();

        if (bewerber.getId() == null) {
            service.addBewerber(bewerber);
        } else
        {
            service.updateBewerber(bewerber.getId(),bewerber);
        }



        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar()
    {
        Button add_btn = new Button("Bewerber hinzufügen",click->addBewerber());
        HorizontalLayout toolbar = new HorizontalLayout(add_btn);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    private void addBewerber()
    {
        grid.asSingleSelect().clear();
        editBewerber(new Bewerber());

    }
    private void configureGrid()
    {
        grid.addClassName("bewerber-grid");
        grid.setSizeFull();
        grid.setColumns("vorname","name","email","telefonNr","gewuenschtePosition","gehaltswunsch");
        grid.addColumn(bewerber->bewerber.getArbeitszeit().toString()).setHeader("Arbeitszeit");
        grid.addColumn(bewerber->bewerber.getBewerberStatus().toString()).setHeader("Bewerberstatus");

        grid.getColumns().forEach(col-> col.setAutoWidth(true));
        
        grid.asSingleSelect().addValueChangeListener(evt-> editBewerber((Bewerber) evt.getValue()));
    }

    private void editBewerber(Bewerber bewerber) {
        if(bewerber==null)
        {
            closeEditor();
        }else{
            form.setBewerber(bewerber);
            form.setVisible(true);
            addClassName("editing");

        }
    }

    private void closeEditor() {
        form.setBewerber(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    public void updateList() {
        grid.setItems(service.getBewerber());



        }
    }



