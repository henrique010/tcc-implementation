package com.example.tcc_implementation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import helpers.Logger;
import modules.matrix.Matrix;
import modules.factorial.Factorial;
import shared.SocketClient;

public class MainActivity extends AppCompatActivity {
    int input;
    String operation;
    String method;

    protected AutoCompleteTextView onCreateDropdownItems(int dropdownId, String[] items) {
        AutoCompleteTextView autoCompleteTextView = findViewById(dropdownId);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, items);
        autoCompleteTextView.setAdapter(arrayAdapter);

        return autoCompleteTextView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Context context = getApplication();

        String [] methods = { "HOMOMORPHIC", "STEGANOGRAPHY" };
        String [] dimensions = { "5", "50", "100", "150", "200", "250", "300", "400", "500", "750", "1000" };
        String [] operations = { "MATRIX ADDITION", "MATRIX MULTIPLICATION", "FACTORIAL" };

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        final Button button = findViewById(R.id.execute);
        AutoCompleteTextView autoCompleteDimension = onCreateDropdownItems(R.id.autoCompleteDimension, dimensions);
        AutoCompleteTextView autoCompleteOperation = onCreateDropdownItems(R.id.autoCompleteOperation, operations);
        AutoCompleteTextView autoCompleteMethods = onCreateDropdownItems(R.id.autoCompleteMethod, methods);

        autoCompleteDimension.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                input = Integer.parseInt(item);
                Toast.makeText(getApplicationContext(), "Input: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteOperation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                operation = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Operation: " + operation, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteMethods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                method = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Method: " + method, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operation.contains("MATRIX")) {
                    Socket socket = SocketClient.connect("192.168.1.11", 3333);
                    Matrix matrix = new Matrix(socket, context);
                    try {
                        int[][] result = matrix.runProcess(method, operation, input);
                        Logger.integerMatrix(result, input);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SocketClient.disconnect(socket);
                }
                else if(operation.equals("FACTORIAL")) {
                    Socket socket = SocketClient.connect("192.168.1.11", 3333);
                    Factorial factorial = new Factorial(socket, context);
                    try {
                        double result = factorial.runProcess(input, method);
                        System.out.println("FACTORIAL: "+result);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SocketClient.disconnect(socket);
                }
            }
        });
    }
}