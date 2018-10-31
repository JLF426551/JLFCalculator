package com.jl426551.example.mycalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/*
An adapter for ButtonData class.
 */
public class ButtonDataAdapter extends RecyclerView.Adapter<ButtonDataAdapter.BDViewHolder> {

    //Variable sets to 0 by default. Updated by constructor
    private int buttonsTotal= 0;

    private ArrayList<ButtonData> buttonList;
    private ButtonClickHandler mButtonDataHandler;

    // The interface that receives onClick messages.
    public interface ButtonClickHandler {
        void onButtonSelected(int charOption);
    }

    public ButtonDataAdapter(ButtonClickHandler handler, ArrayList<ButtonData> list) {
        mButtonDataHandler = handler;
        buttonList = list;
        if (list != null)
            buttonsTotal= list.size();
    }

    @NonNull
    @Override
    public BDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean attachToParent = false;

        View viewToProcess = inflater.inflate(R.layout.single_button_view, parent, attachToParent);

        return new BDViewHolder(viewToProcess);
    }

    @Override
    public void onBindViewHolder(@NonNull BDViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return buttonsTotal;
    }


    public class BDViewHolder extends RecyclerView.ViewHolder{

        Button buttonView;
        Context context;

        BDViewHolder(View viewParam) {
            super(viewParam);
            context = viewParam.getContext();
            buttonView = viewParam.findViewById(R.id.display_button);
        }

        void bind(int position) {

            //Reads position into a temporary variable so it can be used in the OnClickListener
            final int mPlace = position;
            buttonView.setText(buttonList.get(position).getDisplayValue());

            //Sets a custom OnClickListener for each button.
            //Each button calls the ButtonClickHandler with a different parameter.
            buttonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mButtonDataHandler.onButtonSelected(mPlace);
                }
            });
        }

    }
}