package database.OsuTourDbSchema;
/*
 * This class is used to define our database schema. It defines String constants for all table
 * names and columns.
 */
public class OsuTourDbSchema {

    public static final class UserTable {
        public static final String NAME = "user";

        public static final class Cols {
            public static final String USER_NAME = "user_name";
            public static final String PASSWORD = "password";
        }
    }

    public static final class LocationTable {
        public static final String NAME = "location";

        public static final class Cols {
            public static final String LATITUDE = "latitude";
            public static final String LONGITUDE = "longitude";
            public static final String NAME = "name";
            public static final String HISTORY = "history";
        }
    }

    public static final class QuestionTable {
        public static final String NAME = "question";

        public static final class Cols {
            public static final String LOCATION_ID = "location_id";
            public static final String TEXT = "text";
        }
    }

    public static final class AnswerTable {
        public static final String NAME = "answer";

        public static final class Cols {
            public static final String TEXT = "text";
        }
    }

    public static final class QuestionAnswerTable {
        public static final String NAME = "question_answer_xref";

        public static final class Cols {
            public static final String QUESTION_ID = "question_id";
            public static final String ANSWER_ID = "answer_id";
            public static final String IS_CORRECT = "is_correct";
        }
    }

    public static final class LocationQuestionTable {
        public static final String NAME = "location_question_xref";

        public static final class Cols {
            public static final String LOCATION_ID = "location_id";
            public static final String QUESTION_ID = "question_id";
        }
    }

    public static final class PlayerLocationCompletedTable {
        public static final String NAME = "player_locations_completed";

        public static final class Cols {
            public static final String USER_ID = "user_id";
            public static final String LOCATION_ID = "location_id";
        }
    }
}
