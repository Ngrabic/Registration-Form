package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Spinner countriesList ;
    public Button btnRegistration;
    private ConstraintLayout mainConstrain;
    private EditText name , email, password, reEnterPassword;
    private CheckBox iAgree;
    public RadioGroup radioGroup;


    private static final int PICK_IMAGE = 100;
    private ImageView imageView;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        countriesList=findViewById(R.id.countrySpinnerId);

        mainConstrain=findViewById(R.id.mainConstrainId);

        btnRegistration=findViewById(R.id.btnRegisterId);

        //Input
        name=findViewById(R.id.nameId);
        email=findViewById(R.id.emailId);
        password=findViewById(R.id.passwordId);
        reEnterPassword=findViewById(R.id.reEnterPasswordId);

        radioGroup=findViewById(R.id.radioGroupId);

        //CheckBox
        iAgree=findViewById(R.id.iAgreeId);

        imageView=findViewById(R.id.imageView);

        setCountriesList(countriesList);



        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar("You have registered: ");
                checkInputData(radioGroup,iAgree,password,reEnterPassword,name,email);
                addNewPeople(name,email,password,countriesList,radioGroup,imageUri);
                setStartData(name,email,password,reEnterPassword,iAgree);
            }
        });
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openGallery();
               scaleImage(imageView);

           }
       });


        MaterialToolbar toolbar;
        toolbar=findViewById(R.id.topAppBar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(toolbar,SecondaryActivity.class,R.id.search);
            }
        });





    }


    public void addNewPeople(EditText name, EditText email, EditText password,Spinner contries, RadioGroup group,Uri img) {
        if (!(name.getText().toString().equals("")) || !(email.getText().toString().equals("")) || !(password.getText().toString().equals("")))
           SecondaryActivity.peoples.add(new People(name.getText().toString(), email.getText().toString(),password.getText().toString(),contries.getSelectedItem().toString() , group.getCheckedRadioButtonId(),imageUri));
        else
            showSnackbar("Fill all fields");

    }



    public void startNewActivity(Class<?> cls, Context context){
        Intent intent=new Intent(context,cls);
        startActivity(intent);
    }


    public void showPopup(View v, Class<?> cls,int menuItemId) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==menuItemId){
                    startNewActivity(cls,getApplicationContext());
                    return true;
                }else
                return false;
            }
        });
    }



    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }


    private void scaleImage(ImageView view) {
        Drawable drawing = view.getDrawable();
        if (drawing == null) {
            return;
        }
        Bitmap bitmap = Bitmap.createBitmap(drawing.getIntrinsicWidth(), drawing.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        drawing.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawing.draw(canvas);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int xBounding = ((View) view.getParent()).getWidth();//EXPECTED WIDTH
        int yBounding = ((View) view.getParent()).getHeight();//EXPECTED HEIGHT

        float xScale = ((float) xBounding) / width;
        float yScale = ((float) yBounding) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(xScale, yScale);

        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth();
        height = scaledBitmap.getHeight();
        BitmapDrawable result = new BitmapDrawable(getApplicationContext().getResources(), scaledBitmap);

        view.setImageDrawable(result);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }



    private void setStartData(EditText name,EditText email,EditText password, EditText reEnterPassword, CheckBox iAgree) {
        name.setText("");
        email.setText("");
        password.setText("");
        reEnterPassword.setText("");
        iAgree.setChecked(false);
        imageView.setImageURI(null);
    }

    private void checkInputData(RadioGroup radioGroup,CheckBox iAgree, EditText password, EditText reEnterPassword,EditText name,EditText email) {
       if (radioGroup.getCheckedRadioButtonId() == -1  )
         radioGroup.setBackgroundColor(Color.RED);

       if (radioGroup.getCheckedRadioButtonId() != -1 )
           radioGroup.setBackgroundColor(Color.TRANSPARENT);

       if (!(iAgree.isChecked()))
           iAgree.setTextColor(Color.RED);

        if (iAgree.isChecked())
            iAgree.setTextColor(Color.BLACK);

        // is Password same
        if (password.getText().toString().equals(reEnterPassword.getText().toString())) {
            password.setText("");
            reEnterPassword.setText("");
            password.setHint("Password are not same");
            password.setHintTextColor(Color.RED);
        }

        //Input name, email and pass emptiness check

            if (name.getText().toString().equals("")) {
                name.setHint("Unesi ime");
                name.setHintTextColor(Color.RED);
            }else{
                name.setHintTextColor(Color.GRAY);
            }

            if (email.getText().toString().equals("")){
                email.setHint("Unesi email");
                email.setHintTextColor(Color.RED);
            }
            else{
                email.setHintTextColor(Color.GRAY);
            }


            if (password.getText().toString().equals("")){
                password.setHint("Unesi password.....");
                password.setHintTextColor(Color.RED);
            }
            else{
                password.setHintTextColor(Color.GRAY);
            }
    }

    public void setCountriesList(Spinner countriesList){

        ArrayList<String> countries= new ArrayList<>();
        countries.add("Germany");
        countries.add("Croatia");
        countries.add("England");
        countries.add("France");

        ArrayAdapter<String> countriesAdapter=new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1,
                countries);
        countriesList.setAdapter(countriesAdapter);

    }
    public void showSnackbar(String txt){
        Snackbar.make(mainConstrain, txt + SecondaryActivity.peoples.size(),Snackbar.LENGTH_SHORT).show();
    }
}