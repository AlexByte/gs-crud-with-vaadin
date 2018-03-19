package hello;

import com.vaadin.data.Binder;
import com.vaadin.ui.renderers.TextRenderer;
import org.springframework.util.StringUtils;

import org.vaadin.patrik.FastNavigation;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;

@SpringUI
public class VaadinUI extends UI {


    final Grid<Customer> grid;

    public VaadinUI() {
        this.grid = new Grid<>();
    }

    @Override
    protected void init(VaadinRequest request) {
        grid.getEditor().setEnabled(true);
        FastNavigation fastNavigation = new FastNavigation(grid);
        fastNavigation.addClickOutListener(clickOutEvent -> {
            int a = 5;
            grid.setEnabled(false);
        });

        Binder<Customer> binder = grid.getEditor().getBinder();
        grid.addColumn(Customer::getFirstName,
                new TextRenderer()).setEditorBinding(binder
                .forField(new TextField())
                .bind(Customer::getFirstName, Customer::setFirstName)
        );

        grid.addColumn(Customer::getLastName,
                new TextRenderer()).setEditorBinding(binder
                .forField(new TextField())
                .bind(Customer::getLastName, Customer::setLastName)
        );

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Jack", "Bauer"));
        customers.add(new Customer("Chloe", "O'Brian"));
        customers.add(new Customer("Kim", "Bauer"));
        customers.add(new Customer("David", "Palmer"));
        customers.add(new Customer("Michelle", "Dessler"));
        grid.setItems(customers);

        setContent(grid);
    }


}
