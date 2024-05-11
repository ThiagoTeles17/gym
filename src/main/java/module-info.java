module com.gym.gym {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.masked.maskedtextfield to javafx.fxml;
    exports com.masked.maskedtextfield;

    opens com.gym.gym.model;

    exports com.gym.gym.application.ManageClients;
    opens com.gym.gym.application.ManageClients to javafx.fxml;

    exports com.gym.gym.application.ManageClients.Create;
    opens com.gym.gym.application.ManageClients.Create to javafx.fxml;

    exports com.gym.gym.application.ManageClients.Edit;
    opens com.gym.gym.application.ManageClients.Edit to javafx.fxml;




    exports com.gym.gym.application.Login;
    opens com.gym.gym.application.Login to javafx.fxml;
    exports com.gym.gym.application.Home;
    opens com.gym.gym.application.Home to javafx.fxml;
}