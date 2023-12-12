package com.example.lethichinh_qlvattu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Chinh_MainActivity extends AppCompatActivity {

    //Khai báo các thành phần
    private EditText edtMavt, edtTen, edtNsx, edtNamsx, edtSoluong, edtDongia;
    private Button btnThem, btnSua, btnXoa, btnThoat;
    private ListView lvvt;
    private Chinh_VTAdapter adapter;
    private Chinh_VTDatabase database;
    private List<Chinh_VatTu> listVT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        anhxa();

        //Khởi tạo
        database = new Chinh_VTDatabase(Chinh_MainActivity.this);
        listVT = database.ThongTinVT();
        setAdapter();

        //Xứ lý logic
        //Bắt sự kiện cho btn Thoát
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Chinh_MainActivity.this);
                builder.setTitle("Xác nhận thoát chương trình");
                builder.setMessage("Bạn có muốn thoát khỏi chương trình không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                //Hiển thị hộp thoại
                Dialog dialog = builder.create();
                dialog.show();

            }
        });

        //Bắt dự kiện cho btn Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mavt = edtMavt.getText().toString();
                String ten = edtTen.getText().toString();
                String nsx = edtNsx.getText().toString();
                String namsx = edtNamsx.getText().toString();
                String soluong = edtSoluong.getText().toString();
                String dongia = edtDongia.getText().toString();
                Chinh_VatTu vt = new Chinh_VatTu(mavt, ten, nsx, namsx, soluong, dongia);
                if (vt != null) {
                    database.ThemVT(vt);
                }
                reset();
                listVT.clear();
                listVT.addAll(database.ThongTinVT());
                setAdapter();
            }
        });

        //Bắt sự kiện cho việc chọn từng item trong Listview
        lvvt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Chinh_VatTu sv = listVT.get(i);
                //Đưa các thông tin của sv thứ i lên edt
                edtMavt.setText(sv.getMavt());
                edtTen.setText(sv.getTen());
                edtNsx.setText(sv.getNsx());
                edtNamsx.setText(sv.getNamsx());
                edtSoluong.setText(sv.getSoluong());
                edtDongia.setText(sv.getDongia());
                //Ẩn btn Thêm
                btnThem.setEnabled(false);
                edtMavt.setEnabled(false);
            }
        });

        //Bắt sự kiện cho btn Sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chinh_VatTu vt = new Chinh_VatTu();
                vt.setMavt(String.valueOf(edtMavt.getText()));
                vt.setTen(edtTen.getText() + " ");
                vt.setNsx(edtNsx.getText() + " ");
                vt.setNamsx(edtNamsx.getText() + "");
                vt.setSoluong(edtSoluong.getText() + "");
                vt.setDongia(edtDongia.getText() + "");
                int kq = database.CapNhatVT(vt);
                if (kq > 0) {
                    Toast.makeText(Chinh_MainActivity.this, "Cập nhật vật thành công!",
                            Toast.LENGTH_LONG).show();
                    setAdapter();
                    updateListVT();
                } else {
                    Toast.makeText(Chinh_MainActivity.this, "Cập nhật vật tư thất bại!",
                            Toast.LENGTH_LONG).show();
                }
                reset();
                btnThem.setEnabled(true);
            }
        });

        //Bắt sự kiện cho btn Xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chinh_VatTu vt = new Chinh_VatTu();
                vt.setMavt(String.valueOf(edtMavt.getText()));
                vt.setTen(edtTen.getText() + " ");
                vt.setNsx(edtNsx.getText() + " ");
                vt.setNamsx(edtNamsx.getText() + "");
                vt.setSoluong(edtSoluong.getText() + "");
                vt.setDongia(edtDongia.getText() + "");
                int kqxoa = database.XoaVT(vt);
                if (kqxoa > 0) {
                    Toast.makeText(Chinh_MainActivity.this, "Xóa vật tư thành công!",
                            Toast.LENGTH_LONG).show();
                    setAdapter();
                    updateListVT();
                } else {
                    Toast.makeText(Chinh_MainActivity.this, "Xóa vật tư thất bại!",
                            Toast.LENGTH_LONG).show();
                }
                reset();
                btnThem.setEnabled(true);
            }
        });

    }

    private void updateListVT() {
        listVT.clear();
        listVT.addAll(database.ThongTinVT());
    }

    private void reset() {
        edtMavt.setText("");
        edtTen.setText("");
        edtNsx.setText("");
        edtNamsx.setText("");
        edtSoluong.setText("");
        edtDongia.setText("");
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new Chinh_VTAdapter(Chinh_MainActivity.this, R.layout.item_listvt, listVT);
            lvvt.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void anhxa() {
        edtMavt = findViewById(R.id.edt_id);
        edtTen = findViewById(R.id.edt_ten);
        edtNsx = findViewById(R.id.edt_nsx);
        edtNamsx = findViewById(R.id.edt_namsx);
        edtSoluong = findViewById(R.id.edt_soluong);
        edtDongia = findViewById(R.id.edt_dongia);
        btnThem = findViewById(R.id.btn_add);
        btnSua = findViewById(R.id.btn_edit);
        btnXoa = findViewById(R.id.btn_delete);
        btnThoat = findViewById(R.id.btn_cancel);
        lvvt = findViewById(R.id.lv_vt);

    }
}