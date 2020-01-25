package com.example.pencilfactory;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.pencilfactory.R.drawable.button_border;
import static com.example.pencilfactory.R.drawable.ic_body_circle;
import static com.example.pencilfactory.R.drawable.ic_body_hexagonal;
import static com.example.pencilfactory.R.drawable.ic_body_triangle;
import static com.example.pencilfactory.R.drawable.ic_circle_menu;
import static com.example.pencilfactory.R.drawable.ic_circle_short;
import static com.example.pencilfactory.R.drawable.ic_hexagonal_menu;
import static com.example.pencilfactory.R.drawable.ic_hexagonal_short;
import static com.example.pencilfactory.R.drawable.ic_not_sharpen_hexagonal_end;
import static com.example.pencilfactory.R.drawable.ic_not_sharpen_round_end;
import static com.example.pencilfactory.R.drawable.ic_not_sharpen_triangle_end;
import static com.example.pencilfactory.R.drawable.ic_sharpen_menu;
import static com.example.pencilfactory.R.drawable.ic_sharpen_universal_end;
import static com.example.pencilfactory.R.drawable.ic_triangle_menu;
import static com.example.pencilfactory.R.drawable.ic_triangle_short;
import static com.example.pencilfactory.R.drawable.ic_unsharpen_menu;

public class MainActivity extends AppCompatActivity {
    Button lengthButton, chooseFormButton, chooseBodyColorButton,
            chooseSharpenButton, stripButton, dipButton, eraserButton, chooseTextButton, sharingButton, saveButton;
    Button length88mmButton, hexagonalFormButton, triangleFormButton, chooseSharpenTrueButton, chooseSharpenFalseButton,
            length177mmButton, circleFormButton;
    Button[] lengthButtonMenu, chooseFormButtonMenu, allLeftButtonsMassive, chooseSharpenButtonMenu, allRightButtonsMassive, lengthChangeButtonMassive, formChangeButtonMassive;

    ImageView pencilViewMain, sharpenViewMain;
    PencilBuilder pencil = new PencilBuilder();

    List<Color_model> color_list = new ArrayList<>();
    ChooseColorRecyclerAdapter color_adapter;
    RecyclerView recyclerView;



    boolean isAnyMenuShowing, isLengthButtonMenuShowing, isChooseFormButtonMenuShowing, isChooseSharpenButtonMenuShowing;
    boolean[] booleans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        lengthButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isLengthButtonMenuShowing = true;
                if (isChooseFormButtonMenuShowing || isChooseSharpenButtonMenuShowing) {
                    setAllBooleanFalse();
                    isLengthButtonMenuShowing = true;
                }
                setOnRightColumnButtonClick(lengthButton, lengthButtonMenu);
                if (pencil.getLength() == 177) {
                    enableButtonColorFilterByMassive(lengthChangeButtonMassive, length177mmButton);

                } else {
                    enableButtonColorFilterByMassive(lengthChangeButtonMassive, length88mmButton);
                }
            }
        });

        chooseFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChooseFormButtonMenuShowing = true;
                if (isLengthButtonMenuShowing || isChooseSharpenButtonMenuShowing) {
                    setAllBooleanFalse();
                    isChooseFormButtonMenuShowing = true;
                }
                setOnRightColumnButtonClick(chooseFormButton, chooseFormButtonMenu);
                if (pencil.getCorners() == 6) {
                    enableButtonColorFilterByMassive(formChangeButtonMassive, hexagonalFormButton);
                } else if (pencil.getCorners() == 3) {
                    enableButtonColorFilterByMassive(formChangeButtonMassive, triangleFormButton);
                } else {
                    enableButtonColorFilterByMassive(formChangeButtonMassive, circleFormButton);
                }

            }
        });

        chooseSharpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChooseSharpenButtonMenuShowing = true;
                if (isLengthButtonMenuShowing || isChooseFormButtonMenuShowing) {
                    setAllBooleanFalse();
                    isChooseSharpenButtonMenuShowing = true;

                }
                setOnRightColumnButtonClick(chooseSharpenButton, chooseSharpenButtonMenu);
                if (pencil.isSharpening()) {
                    enableButtonColorFilterByMassive(formChangeButtonMassive, chooseSharpenTrueButton);
                } else {
                    enableButtonColorFilterByMassive(formChangeButtonMassive, chooseSharpenFalseButton);
                }


            }
        });

        length88mmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pencil.getCorners() == 6) {
                    pencilViewMain.setImageResource(ic_hexagonal_short);
                } else if (pencil.getCorners() == 3) {
                    pencilViewMain.setImageResource(ic_triangle_short);
                } else {
                    pencilViewMain.setImageResource(ic_circle_short);
                }
                pencil.setLength(88);
                enableButtonColorFilterByMassive(lengthChangeButtonMassive, length88mmButton);
            }
        });

        length177mmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pencil.getCorners() == 6) {
                    pencilViewMain.setImageResource(ic_body_hexagonal);
                } else if (pencil.getCorners() == 3) {
                    pencilViewMain.setImageResource(ic_body_triangle);
                } else {
                    pencilViewMain.setImageResource(ic_body_circle);
                }

                pencil.setLength(177);
                enableButtonColorFilterByMassive(lengthChangeButtonMassive, length177mmButton);

            }
        });

        circleFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pencil.isSharpening())
                    sharpenViewMain.setImageResource(ic_not_sharpen_round_end);
                if (pencil.getLength() == 177) {
                    pencilViewMain.setImageResource(ic_body_circle);
                } else {
                    pencilViewMain.setImageResource(ic_circle_short);
                }
                pencil.setCorners(0);
                setForegroundWithFilter(chooseFormButton, ic_circle_menu);
                enableButtonColorFilterByMassive(formChangeButtonMassive, circleFormButton);
            }
        });

        hexagonalFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pencil.isSharpening())
                    sharpenViewMain.setImageResource(ic_not_sharpen_hexagonal_end);
                if (pencil.getLength() == 177) {
                    pencilViewMain.setImageResource(ic_body_hexagonal);
                } else {
                    pencilViewMain.setImageResource(ic_hexagonal_short);
                }
                pencil.setCorners(6);
                setForegroundWithFilter(chooseFormButton, ic_hexagonal_menu);
                enableButtonColorFilterByMassive(formChangeButtonMassive, hexagonalFormButton);
            }
        });

        triangleFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pencil.isSharpening())
                    sharpenViewMain.setImageResource(ic_not_sharpen_triangle_end);
                if (pencil.getLength() == 177) {
                    pencilViewMain.setImageResource(ic_body_triangle);
                } else {
                    pencilViewMain.setImageResource(ic_triangle_short);
                }
                pencil.setCorners(3);
                setForegroundWithFilter(chooseFormButton, ic_triangle_menu);
                enableButtonColorFilterByMassive(formChangeButtonMassive, triangleFormButton);
            }
        });

        chooseSharpenTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharpenViewMain.setImageResource(ic_sharpen_universal_end);
                Drawable d = sharpenViewMain.getDrawable();
                d.clearColorFilter();
                pencil.setSharpening(true);
                setForegroundWithFilter(chooseSharpenButton, ic_sharpen_menu);
                enableButtonColorFilterByMassive(chooseSharpenButtonMenu, chooseSharpenTrueButton);


            }
        });

        chooseSharpenFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pencil.setSharpening(false);
                Drawable d = pencilViewMain.getDrawable();
                if (pencil.getCorners() == 0) {

                    sharpenViewMain.setImageResource(ic_not_sharpen_round_end);
                    sharpenViewMain.setColorFilter(d.getColorFilter());
                } else if (pencil.getCorners() == 3) {
                    sharpenViewMain.setImageResource(ic_not_sharpen_triangle_end);
                    sharpenViewMain.setColorFilter(d.getColorFilter());
                } else if (pencil.getCorners() == 6) {
                    sharpenViewMain.setImageResource(ic_not_sharpen_hexagonal_end);
                    sharpenViewMain.setColorFilter(d.getColorFilter());
                }

                setForegroundWithFilter(chooseSharpenButton, ic_unsharpen_menu);
                enableButtonColorFilterByMassive(chooseSharpenButtonMenu, chooseSharpenFalseButton);
            }

        });

        chooseBodyColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnRightColumnButtonClick(chooseBodyColorButton,recyclerView);
            }
        });


        color_adapter.SetOnItemClickListener(new ChooseColorRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                paintPencil(pencilViewMain,sharpenViewMain,position);

            }
        });
    }

    private void paintPencil(ImageView body, ImageView ending, int position) {
        if(position==0){
            clearPencil();
        }else {
            if (!pencil.isSharpening()) {
                ending.setColorFilter(ContextCompat.getColor(getApplicationContext(), color_list.get(position).getColor()), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
            body.setColorFilter(ContextCompat.getColor(getApplicationContext(), color_list.get(position).getColor()),
                    android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    public void clearPencil(){
        Drawable body = pencilViewMain.getDrawable();
        Drawable ending = sharpenViewMain.getDrawable();
        if(!pencil.isSharpening()){
            ending.clearColorFilter();
        }
        body.clearColorFilter();
    }

    private void setForegroundWithFilter(Button button, int resource) {
        button.setForeground(getDrawable(resource));
        enableButtonColorFilter(button);

    }


    void setOnRightColumnButtonClick(Button button, Button[] buttons) {
        if (!isAnyMenuShowing) {
            clearScreen();
            for (Button b : buttons) b.setVisibility(View.VISIBLE);
            enableButtonColorFilter(button);
            isAnyMenuShowing = true;
        } else {
            clearScreen();
            isAnyMenuShowing = false;
        }
    }

    void setOnRightColumnButtonClick(Button button, RecyclerView recyclerView) {
        if (!isAnyMenuShowing) {
            clearScreen();
            recyclerView.setVisibility(View.VISIBLE);
            enableButtonColorFilter(button);
            isAnyMenuShowing = true;
        } else {
            clearScreen();
            isAnyMenuShowing = false;
        }
    }

//    void setOnLeftColumnButtonClick(Button button) {
//        if (!isAnyMenuShowing) {
//            disableButtonColorFilterByMassive(allRightButtonsMassive);
//            enableButtonColorFilter(button);
//        } else {
//            disableButtonColorFilterByMassive(allRightButtonsMassive);
//            clearScreen();
//        }
//    }


    void clearScreen(){
        setAllLeftButtonsInvisible();
        disableButtonColorFilterByMassive(allRightButtonsMassive);

    }



    void setAllLeftButtonsInvisible() {
        for (Button b : allLeftButtonsMassive) b.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void disableButtonColorFilterByMassive(Button[]buttons) {
        for (Button b : buttons) {
            Drawable d = b.getForeground();
            b.invalidateDrawable(d);
            d.clearColorFilter();
            b.setBackground(getDrawable(button_border));
        }

    }

    private void enableButtonColorFilterByMassive(Button[]buttons, Button button) {
        for (Button b : buttons) {
            Drawable d = b.getForeground();
            b.invalidateDrawable(d);
            d.clearColorFilter();
            b.setBackground(getDrawable(button_border));
        }
        enableButtonColorFilter(button);

    }
    private void enableButtonColorFilter(Button button) {
        button.getForeground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
       // button.setBackgroundColor(getColor(R.color.colorGrey));
    }




    void setAllBooleanFalse() {
        isAnyMenuShowing = false;
        isLengthButtonMenuShowing = false;
        isChooseFormButtonMenuShowing = false;
        isChooseSharpenButtonMenuShowing = false;

    }

    private void init() {
        pencilViewMain=findViewById(R.id.pencilViewMain);
        sharpenViewMain=findViewById(R.id.sharpen_main_view);
        lengthButton = findViewById(R.id.bodyLengthButton);
        length88mmButton = findViewById(R.id.bodyLengthButtonLeft);
        circleFormButton = findViewById(R.id.bodyLengthButtonLeftAlt);
        chooseFormButton = findViewById(R.id.chooseFormButton);
        hexagonalFormButton = findViewById(R.id.chooseFormButtonLeft);
        chooseBodyColorButton = findViewById(R.id.chooseColourButton);
        triangleFormButton = findViewById(R.id.chooseColourButtonLeft);
        length88mmButton = findViewById(R.id.bodyLengthButtonLeft);
        hexagonalFormButton = findViewById(R.id.chooseFormButtonLeft);
        triangleFormButton = findViewById(R.id.chooseColourButtonLeft);
        chooseSharpenButton = findViewById(R.id.chooseSharpenButton);
        chooseSharpenTrueButton = findViewById(R.id.chooseSharpenButtonLeft);

        chooseSharpenFalseButton = findViewById(R.id.chooseStripButtonLeft);
        length177mmButton = findViewById(R.id.chooseFormButtonLeftAlt);
        stripButton = findViewById(R.id.chooseStripsButton);
        dipButton = findViewById(R.id.chooseDipButton);
        eraserButton = findViewById(R.id.chooseEraserButton);
        chooseTextButton = findViewById(R.id.chooseBodyTextButton);
        saveButton = findViewById(R.id.saveButton);
        sharingButton = findViewById(R.id.shareButton);



        init_color_adapter();







        lengthButtonMenu = new Button[]{
                length88mmButton,
                length177mmButton
        };
        chooseFormButtonMenu = new Button[]{
                circleFormButton,
                hexagonalFormButton,
                triangleFormButton
        };
        chooseSharpenButtonMenu = new Button[]{
                chooseSharpenTrueButton,
                chooseSharpenFalseButton

        };

        allLeftButtonsMassive = new Button[]{
                length88mmButton,
                hexagonalFormButton,
                triangleFormButton,
                chooseSharpenTrueButton,
                chooseSharpenFalseButton,
                length177mmButton,
                circleFormButton
        };

        allRightButtonsMassive = new Button[]{
                lengthButton,
                chooseFormButton,
                chooseBodyColorButton,
                chooseSharpenButton

        };

        lengthChangeButtonMassive=new Button[]{
         length88mmButton,length177mmButton
        };
        formChangeButtonMassive=new Button[]{
                circleFormButton,hexagonalFormButton,triangleFormButton
        };
        booleans = new boolean[]{
                isAnyMenuShowing,
        };

    }

    private void init_color_adapter() {
        for (int color:Color_model.getColors()) {
            color_list.add(new Color_model(color));
        }

        recyclerView = findViewById(R.id.recycler_choose_color_view);
        color_adapter = new ChooseColorRecyclerAdapter(this,color_list);
        recyclerView.setAdapter(color_adapter);
    }




}

