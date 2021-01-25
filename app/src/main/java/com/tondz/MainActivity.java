package com.tondz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> dssv;
    ListView list_item;
    Button add,delete;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dssv = new ArrayList<>();
        list_item = findViewById(R.id.list_item);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        editText = findViewById(R.id.edit_query);
        //Tạo adapter listview
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,
                dssv
        );
        //sự kiện khi click
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    //thêm dữ liệu từ edittext vào mảng
                    dssv.add(editText.getText().toString());
                    //set lại listview
                    list_item.setAdapter(arrayAdapter);
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this,"Dm dell có chữ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //tạo hộp thoại thông báo
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        //thiết lập tiêu đề(title)
                        alert.setTitle("Xác nhận");
                        //thiết lập thông báo
                        alert.setMessage("Mày có muốn xóa hay không?");
                        //thiết lập icon
                        alert.setIcon(R.mipmap.ic_notify);
                        //thiết lập nút đồng
                        alert.setPositiveButton("Bố mày đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"Đã xóa "+dssv.get(position),Toast.LENGTH_SHORT).show();
                                //xóa đối tượng vừa click, position là vị trí của đối tượng mình trỏ vào
                                dssv.remove(position);
                                list_item.setAdapter(arrayAdapter);
                            }
                        });
                        alert.setNegativeButton("Tao đéo đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"Không xóa thì thôi ",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                        alert.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              dialog.dismiss();
                            }
                        });
                        alert.show();
                    }
                });
            }
        });

    }

}