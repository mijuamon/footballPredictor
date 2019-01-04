package com.mijuamon.core.file;

import static com.mijuamon.core.constants.Constants.*;
import java.util.ArrayList;
import java.util.List;

public class fileLoader {

    private static fileLoader instance = null;
    protected fileLoader() {
        // Exists only to defeat instantiation.
    }
    public static fileLoader getInstance() {
        if(instance == null) {
            instance = new fileLoader();
        }
        return instance;
    }

    private List<String> loadFile(String url)
    {
        List<String> output = new ArrayList<>();

        return output;
    }
}
