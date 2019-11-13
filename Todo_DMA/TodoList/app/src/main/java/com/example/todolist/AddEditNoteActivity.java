package com.example.todolist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.todolist.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.todolist.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.todolist.EXTRA_PRIORITY";
    public static final String EXTRA_ID = "com.example.todolist.EXTRA_ID";

    private EditText editTextTitle;
    private EditText editTextDiscription;
    private NumberPicker numberPickerPriority;

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDiscription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        //getting Intent value
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDiscription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }
        else {
            setTitle("Add Note");
        }

    }

    private void saveNote(){
        String title = editTextTitle.getText().toString();
        String description = editTextDiscription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(AddEditNoteActivity.this, "Please the title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        //for update
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();

    }


    private void exportNote() throws IOException {

        String exportTitle = editTextTitle.getText().toString().trim();
        String exportDescription = editTextDiscription.getText().toString().trim();
        if (exportTitle.isEmpty() || exportDescription.isEmpty()){
            Toast.makeText(this, "Some field are empty", Toast.LENGTH_SHORT).show();

        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions,WRITE_EXTERNAL_STORAGE_CODE);
                }
                else {
                    Savetotxtfile(exportTitle, exportDescription);

                }
            }
            else {
                Savetotxtfile(exportTitle, exportDescription);
            }
        }
    }

    private void Savetotxtfile(String exportTitle1, String exportDescription1) throws IOException {


        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path+"/ToDoList_Document/");
        dir.mkdirs();
        String filename =  exportTitle1+".txt";


        File file = new File(dir, filename);
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(exportDescription1);
        bw.close();
        Toast.makeText(this,"File "+"'"+filename+"'"+" exported to "+dir,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            case R.id.export_note:
                try {
                    exportNote();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
