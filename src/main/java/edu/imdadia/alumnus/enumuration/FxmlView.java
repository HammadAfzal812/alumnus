package edu.imdadia.alumnus.enumuration;

import java.util.ResourceBundle;

public enum FxmlView {

    HOME {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("home.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/home.fxml";
        }
    },
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/login.fxml";
        }
    },


    ADMIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("admin.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/admin.fxml";
        }
    },

    ALUMNUS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("alumnus.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/alumnus.fxml";
        }
    },
    ADMIN_INFO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("adminInfo.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/adminInfo.fxml";
        }
    },

    USER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/user.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    static String getStringFromResourceBundle(final String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }


}
