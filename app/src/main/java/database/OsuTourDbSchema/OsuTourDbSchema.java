package database.OsuTourDbSchema;
/*
 * This class is used to define our database schema. It defines String constants for all table
 * names and columns.
 */
public class OsuTourDbSchema {

    public static final class UserTable {
        public static final String NAME = "user";

        public static final class Cols {
            static final String ID = "id";
            public static final String USER_NAME = "user_name";
            public static final String PASSWORD = "password";
        }
    }

    static final class LocationTable {
        static final String NAME = "location";

        static final class Cols {
            static final String ID = "id";
            static final String LATITUDE = "latitude";
            static final String LONGITUDE = "longitude";
            static final String NAME = "name";
            static final String HISTORY = "history";
        }
    }

    static final class QuestionTable {
        static final String NAME = "question";

        static final class Cols {
            static final String ID = "id";
            static final String TEXT = "text";
        }
    }

    static final class AnswerTable {
        static final String NAME = "answer";

        static final class Cols {
            static final String ID = "id";
            static final String TEXT = "text";
        }
    }

    static final class QuestionAnswerTable {
        static final String NAME = "question_answer_xref";

        static final class Cols {
            static final String ID = "id";
            static final String QUESTION_ID = "question_id";
            static final String ANSWER_ID = "answer_id";
            static final String IS_CORRECT = "is_correct";
        }
    }

    static final class LocationQuestionTable {
        static final String NAME = "location_question_xref";

        static final class Cols {
            static final String ID = "id";
            static final String LOCATION_ID = "location_id";
            static final String QUESTION_ID = "question_id";
        }
    }

    static final class PlayerLocationCompletedTable {
        static final String NAME = "player_locations_completed";

        static final class Cols {
            static final String ID = "id";
            static final String USER_ID = "user_id";
            static final String LOCATION_ID = "location_id";
        }
    }
}
