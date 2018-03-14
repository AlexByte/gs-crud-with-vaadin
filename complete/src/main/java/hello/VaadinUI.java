package hello;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.TextRenderer;
import org.vaadin.patrik.FastNavigation;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

public class VaadinUI extends UI {

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = VaadinUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    final Grid<Customer> grid;

    public VaadinUI() {
        this.grid = new Grid<>();
    }

    @Override
    protected void init(VaadinRequest request) {
        grid.getEditor().setEnabled(true);
        new FastNavigation(grid);

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
