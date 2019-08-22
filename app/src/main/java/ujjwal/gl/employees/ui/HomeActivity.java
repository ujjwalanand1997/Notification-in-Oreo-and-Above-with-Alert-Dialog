package ujjwal.gl.employees.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ujjwal.gl.employees.R;
import ujjwal.gl.employees.adapter.EmployeesAdapter;
import ujjwal.gl.employees.pojo.Employee;

import static ujjwal.gl.employees.constants.Constants.*;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    ImageButton mImport;
    RecyclerView mList;
    List<Employee> mEmployeeList;
    EmployeesAdapter mAdapter;
    Boolean mImageSelected;
    Uri employeeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        initViews();

        mList.hasFixedSize();
        mList.setLayoutManager(new LinearLayoutManager(this));

        mList.setAdapter(mAdapter);
        mImport.setOnClickListener(this);
    }

    private void initViews() {
        mImport = findViewById(R.id.import_btn);
        mList = findViewById(R.id.rec_list);

        mEmployeeList = new ArrayList<>();

        mAdapter = new EmployeesAdapter(this,mEmployeeList);

    }

    @Override
    public void onClick(View view) {
        if(view==mImport){
            showDialog();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            mImageSelected = true;
            employeeImage = data.getData();
            Toast.makeText(getApplicationContext(),mImageSelected.toString(),Toast.LENGTH_LONG).show();
        }else {
            mImageSelected = false;
        }


    }

    public void setAdapter(String employeeName){

        mEmployeeList.add(new Employee(employeeName,employeeImage));
        mAdapter.notifyDataSetChanged();

    }

    public void showDialog(){

        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.enter_detail_layout,viewGroup,false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        final EditText enterName = dialogView.findViewById(R.id.enter_name);
        ImageButton importFile = dialogView.findViewById(R.id.import_file);
        ImageButton next = dialogView.findViewById(R.id.next);

        importFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpg");
                startActivityForResult(intent,FILE_REQUEST_CODE);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enterName.getText().toString();

                if(!TextUtils.isEmpty(name) && mImageSelected){
                    setAdapter(name);
                    mImageSelected = false;
                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.toast_enter_details,Toast.LENGTH_LONG).show();
                }
            }
        });


        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
