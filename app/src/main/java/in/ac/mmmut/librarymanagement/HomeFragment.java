package in.ac.mmmut.librarymanagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    String name, password, email,branch,year,phone,username,roll_no;
    TextView nameTV, emailTV, passwordTV,rollTV,branchTV,yearTV,phoneTV,usernameTV;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        nameTV = (TextView)view.findViewById(R.id.ac_name);
        rollTV=(TextView)view.findViewById(R.id.ac_roll_no);
        branchTV=(TextView)view.findViewById(R.id.ac_branch);
        yearTV=(TextView)view.findViewById(R.id.ac_year);
        emailTV = (TextView) view.findViewById(R.id.ac_email);
        phoneTV=(TextView)view.findViewById(R.id.ac_phone);
        usernameTV=(TextView)view.findViewById(R.id.ac_username);
        passwordTV=(TextView)view.findViewById(R.id.ac_password);


        name = getActivity().getIntent().getStringExtra("name");
        roll_no=getActivity().getIntent().getStringExtra("roll_no");
        branch=getActivity().getIntent().getStringExtra("branch");
        year=getActivity().getIntent().getStringExtra("year");
        email=getActivity().getIntent().getStringExtra("email");
        phone=getActivity().getIntent().getStringExtra("phone");
        username=getActivity().getIntent().getStringExtra("username");
        password=getActivity().getIntent().getStringExtra("password");


        if(username!=null) {
            nameTV.setText("Welcome " + name);
            rollTV.setText("Your Roll No. is " + roll_no);
            branchTV.setText("Your Branch is " + branch);
            yearTV.setText("You are in " + year + " year");
            emailTV.setText("Your email Id is " + email);
            phoneTV.setText("Your contact no. is " + phone);
            usernameTV.setText("Your username is " + username);
            passwordTV.setText("Your password is " + password);
        }
        else
        {
            getActivity().getFragmentManager().popBackStackImmediate();
            getActivity().finish();
            Toast.makeText(getActivity().getApplicationContext(), "Incorrect Username or password !!! ", Toast.LENGTH_LONG).show();

        }
        return view;
    }

}
