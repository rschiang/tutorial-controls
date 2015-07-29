package tw.edu.tp.cksh.funwithcontrols;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends Activity {

    public static final int PICK_IMAGE = 307;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) this.findViewById(R.id.show_tips_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });

        Button button2 = (Button) this.findViewById(R.id.show_image_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPickImage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick() {
        TextView label = (TextView) this.findViewById(R.id.travel_tips_label);
        label.setVisibility(View.VISIBLE);
        // 這裡會被呼叫喔owo)
    }

    public void onPickImage() {
        Intent imagePickerIntent = new Intent(Intent.ACTION_PICK);
        imagePickerIntent.setType("image/*");
        this.startActivityForResult(imagePickerIntent, this.PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case MainActivity.PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();

                    TextView label = (TextView) this.findViewById(R.id.hello_world_label);
                    label.setText(imageUri.toString());

                    try {
                        InputStream stream = this.getContentResolver().openInputStream(imageUri);
                        Drawable drawable = new BitmapDrawable(getResources(), stream);

                        RelativeLayout layout = (RelativeLayout) this.findViewById(R.id.layout);
                        layout.setBackground(drawable);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
