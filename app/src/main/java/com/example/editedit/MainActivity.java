package com.example.editedit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button saveButton;
    private Button loadButton;
    private String filePath = "/sdcard/Download/test.txt"; // Update the path accordingly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile();
            }
        });
    }

    private void saveToFile() {
        String textToSave = editText.getText().toString();

        try {
            File file = new File(filePath);

            FileWriter writer = new FileWriter(file);
            writer.write(textToSave);
            writer.close();
            editText.setText("");
            editText.setHint("Text saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFromFile() {
        try {
            File file = new File(filePath);

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder text = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }

            reader.close();
            editText.setText(text.toString());
            editText.setHint("Loaded from " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading file", Toast.LENGTH_SHORT).show();
        }
    }
}
