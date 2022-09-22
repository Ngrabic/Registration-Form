package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ListOfPeopleAcitivity extends AppCompatActivity {

    public void setNameData(TextView nameData) {
        this.nameData = nameData;
    }

    public TextView nameData, emailData, passwordData,genderData, countryData;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_people_acitivity);

        nameData=findViewById(R.id.nameDataId);
        emailData=findViewById(R.id.emailDataId);
        passwordData=findViewById(R.id.passDataId);
        genderData=findViewById(R.id.genderDataId);
        countryData=findViewById(R.id.countryDataId);
        imgView=findViewById(R.id.imgViewId);

        Bundle b=getIntent().getExtras();
        String name=b.getString("name");
        String email=b.getString("email");
        String pass=b.getString("pass");
        String country=b.getString("country");
        int gender=b.getInt("gender");
        switch (gender){
            case R.id.maleId:
                genderData.setText("Male");
                break;

            case R.id.femaleId:
                genderData.setText("Female");
                break;

            case R.id.otherId:
                genderData.setText("Other");
                break;
        }    //For Selecting which item is selected in RadioGroup

        nameData.setText(name);
        emailData.setText(email);
        passwordData.setText(pass);
        countryData.setText(country);
        imgView.setImageURI(PeopleRecViewAdatper.peoples.get(0).getImage());
    }

}