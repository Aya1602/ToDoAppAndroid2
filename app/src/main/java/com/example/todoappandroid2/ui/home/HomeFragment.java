package com.example.todoappandroid2.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoappandroid2.App;
import com.example.todoappandroid2.OnItemClickListener;
import com.example.todoappandroid2.R;
import com.example.todoappandroid2.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskAdapter = new TaskAdapter();
        List<Task> list = App.getAppDataBase().taskDao().getAll();
        taskAdapter.addItems(list);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(null);
            }
        });
        setFragmentListener();

        initRecycler(view);

        initList();
    }

    private void initList() {
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Task task = taskAdapter.getItem(position);
                openFragment(task);
                Toast.makeText(requireContext(), task.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                Task task = taskAdapter.getItem(position);
                new AlertDialog.Builder(requireContext())
                        .setMessage("Удалить запись \"" + task.getTitle() + "\"?")
                        .setNegativeButton("Нет", null)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                taskAdapter.removeItem(position);
                            }
                        }).show();
            }
        });
    }

    private void initRecycler(View view) {
        taskAdapter = new TaskAdapter();
//        ArrayList<String> list = new ArrayList<>();
//        list.add("sadd");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(taskAdapter);
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("rk_form", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Task task = (Task) result.getSerializable("task");
                String text = result.getString("text");
                taskAdapter.addItem(task);
                Log.e("Home", "text: " + task.getTitle());
                //taskAdapter.addItems(task);

            }
        });
    }
    private void openFragment(Task task) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putSerializable("task", task);
        navController.navigate(R.id.formFragment);
    }
}