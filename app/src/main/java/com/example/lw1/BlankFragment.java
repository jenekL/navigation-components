package com.example.lw1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lw1.DAO.ArchiveDAO;
import com.example.lw1.DAO.EnglishWordsDAO;
import com.example.lw1.database.AppDatabase;
import com.example.lw1.entities.Archive;
import com.example.lw1.entities.EnglishWords;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }


    private AppDatabase db;
    private EnglishWordsDAO englishWordsDAO;
    private ArchiveDAO archiveDAO;
    private ArrayList<EnglishWords> all;
    private List<Button> buttons = new ArrayList<>();

    Intent intent;
    TextToSpeech tts;

    TextView engword;
    TextView num;
    Menu menu;
    Button btn1;
    Button btn2;
    Button btn3;
    Button listenbtn;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db = App.getInstance().getDatabase();
        englishWordsDAO = db.englishWordsDAO();
        archiveDAO = db.archiveDAO();

        tts = new TextToSpeech(getContext(), initStatus -> {
            if (initStatus == TextToSpeech.SUCCESS) {
                if (tts.isLanguageAvailable(new Locale(Locale.getDefault().getLanguage()))
                        == TextToSpeech.LANG_AVAILABLE) {
                    tts.setLanguage(new Locale(Locale.getDefault().getLanguage()));
                } else {
                    tts.setLanguage(Locale.US);
                }
                tts.setPitch(1.3f);
                tts.setSpeechRate(0.7f);
                //  ttsEnabled = true;
            } else if (initStatus == TextToSpeech.ERROR) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
                // ttsEnabled = false;
            }
        });

        String utteranceId = this.hashCode() + "";
        tts.speak("das", TextToSpeech.QUEUE_FLUSH, null, utteranceId);

//        db.clearAllTables();
//
//        englishWordsDAO.insert(new EnglishWords("translate", "перевод"));
//        englishWordsDAO.insert(new EnglishWords("cook", "готовить"));
//        englishWordsDAO.insert(new EnglishWords("son", "сын"));
//        englishWordsDAO.insert(new EnglishWords("kek", "кек"));
//        englishWordsDAO.insert(new EnglishWords("boy", "мальчик"));
//        englishWordsDAO.insert(new EnglishWords("liar", "лжец"));
//        englishWordsDAO.insert(new EnglishWords("still", "все еще"));
//        englishWordsDAO.insert(new EnglishWords("come", "выходи"));



        engword = view.findViewById(R.id.eng);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        listenbtn = view.findViewById(R.id.listenbtn);
        num = view.findViewById(R.id.num);

        all = (ArrayList<EnglishWords>) englishWordsDAO.getAll();

        setAll();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setAll(){
        if(all.size() == 0){
            Toast.makeText(getActivity(), "Слова закончились, добавьте новые", Toast.LENGTH_SHORT).show();
            btn1.setVisibility(Button.INVISIBLE);
            btn2.setVisibility(Button.INVISIBLE);
            btn3.setVisibility(Button.INVISIBLE);
            listenbtn.setVisibility(Button.INVISIBLE);
            engword.setVisibility(TextView.INVISIBLE);

        } else {

            buttons.clear();
            buttons.add(btn1);
            buttons.add(btn2);
            buttons.add(btn3);

            for(Button b: buttons){
                b.setVisibility(Button.VISIBLE);
                b.setBackgroundColor(Color.GRAY);
            }
            listenbtn.setVisibility(Button.VISIBLE);
            engword.setVisibility(TextView.VISIBLE);



            EnglishWords engW = all.get(new Random().nextInt(all.size()));
            engword.setText(engW.getEngWord());

            listenbtn.setOnClickListener(v-> {
                TTS.getInstance().setTextToSpeech(engW.getEngWord());
                String utteranceId = this.hashCode() + "";
                tts.speak(engW.getEngWord(), TextToSpeech.QUEUE_FLUSH, null, utteranceId);
            });

            Button btn = buttons.get(new Random().nextInt(buttons.size()));
            btn.setText(engW.getTranslate());
            btn.setOnClickListener(v -> {
                if(all.size() != 0 && englishWordsDAO.getCounter(engW.getEngWord()) == 2) {
                    archiveDAO.insert(new Archive(engW));
                    englishWordsDAO.delete(engW);
                    all.remove(engW);
                    setAll();
                } else {
                    englishWordsDAO.updateCounter(engW.getEngWord());
                    setAll();
                }
            });

            buttons.remove(btn);

            for (Button b : buttons) {
                b.setOnClickListener(n -> b.setBackgroundColor(Color.RED));
                b.setText(all.get(new Random().nextInt(all.size())).getTranslate());
            }

            num.setText(new StringBuilder(all.size() + " "));

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(0, 1, 0, "Добавить слово").setOnMenuItemClickListener(item -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);
            navController.navigate(R.id.action_blankFragment_to_add2);
            return true;
        });

        menu.add(0, 2, 0, "База").setOnMenuItemClickListener(item -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);
            navController.navigate(R.id.action_blankFragment_to_listFragment);
            return true;
        });
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
