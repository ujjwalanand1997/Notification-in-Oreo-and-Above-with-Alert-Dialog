package ujjwal.gl.employees.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ujjwal.gl.employees.R;
import ujjwal.gl.employees.notification.NotificationHelper;
import ujjwal.gl.employees.pojo.Employee;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>{

    private final Context mContext;
    List<Employee> mEmployeeList;
    NotificationHelper notificationHelper;


    public EmployeesAdapter(Context mContext, List<Employee> mEmployeeList) {
        this.mContext = mContext;
        this.mEmployeeList = mEmployeeList;
    }

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.detail_layout,null);
        EmployeesViewHolder employeesViewHolder = new EmployeesViewHolder(view);

        return employeesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EmployeesViewHolder holder, final int position) {

        notificationHelper = new NotificationHelper(mContext);
        holder.imageImported.setImageURI(mEmployeeList.get(position).getImage());
        holder.imageName.setText(mEmployeeList.get(position).getName());

        holder.imageImported.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext,"imported",Toast.LENGTH_LONG).show();
                notificationHelper.createNotification("Image",holder.imageName.getText().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    class EmployeesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageImported;
        TextView imageName;

        public EmployeesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageImported = itemView.findViewById(R.id.image_imported);
            imageName = itemView.findViewById(R.id.name);
        }
    }
}
