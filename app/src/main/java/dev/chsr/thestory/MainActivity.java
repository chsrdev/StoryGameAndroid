package dev.chsr.thestory;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

class Option {
    String text;
    Runnable onPick;

    Option(String text, Runnable onPick) {
        this.text = text;
        this.onPick = onPick;
    }
}

class Situation {
    String story;
    Option[] options;

    public Situation(String story, Option[] options) {
        this.story = story;
        this.options = options;
    }
}

public class MainActivity extends AppCompatActivity {
    TextView storyText;
    Button[] optionButtons = new Button[3];
    Button optionButton1;
    Button optionButton2;
    Button optionButton3;
    Situation[] situations;
    String[] endings;
    int situationId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
        storyText = findViewById(R.id.story);
        optionButton1 = findViewById(R.id.optionButton1);
        optionButton2 = findViewById(R.id.optionButton2);
        optionButton3 = findViewById(R.id.optionButton3);

        optionButtons = new Button[]{optionButton1, optionButton2, optionButton3};
        for (int i = 0; i < optionButtons.length; i++) {
            final int x = i;
            optionButtons[i].setOnClickListener(view -> handleOption(x));
        }

        startSituation(0);
    }

    private void init() {
        situations = new Situation[]{
                new Situation( // 0
                        getString(R.string.story_0),
                        new Option[]{
                                new Option(getString(R.string.option_0_1), () -> startSituation(1)),
                                new Option(getString(R.string.option_0_2), () -> startSituation(2)),
                                new Option(getString(R.string.option_0_3), () -> startEnding(0))
                        }
                ),
                new Situation( // 1
                        getString(R.string.story_1),
                        new Option[]{
                                new Option(getString(R.string.option_1_1), () -> startSituation(3)),
                                new Option(getString(R.string.option_1_2), () -> startSituation(4)),
                                new Option(getString(R.string.option_1_3), () -> startEnding(3))
                        }
                ),
                new Situation( // 2
                        getString(R.string.story_2),
                        new Option[]{
                                new Option(getString(R.string.option_2_1), () -> startSituation(1)),
                                new Option(getString(R.string.option_2_2), () -> startEnding(4)),
                                new Option(getString(R.string.option_2_3), () -> startEnding(0))
                        }
                ),
                new Situation( // 3
                        getString(R.string.story_3),
                        new Option[]{
                                new Option(getString(R.string.option_3_1), () -> startSituation(4)),
                                new Option(getString(R.string.option_3_2), () -> startEnding(5)),
                                new Option(getString(R.string.option_3_3), () -> startEnding(5))
                        }
                ),
                new Situation( // 4
                        getString(R.string.story_4),
                        new Option[]{
                                new Option(getString(R.string.option_4_1), () -> startSituation(5)),
                                new Option(getString(R.string.option_4_2), () -> startEnding(2)),
                                new Option(getString(R.string.option_4_3), () -> startSituation(5))
                        }
                ),
                new Situation( // 5
                        getString(R.string.story_5),
                        new Option[]{
                                new Option(getString(R.string.option_5_1), () -> startSituation(6)),
                                new Option(getString(R.string.option_5_2), () -> startEnding(7)),
                                new Option(getString(R.string.option_5_3), () -> startSituation(8))
                        }
                ),
                new Situation( // 6
                        getString(R.string.story_6),
                        new Option[]{
                                new Option(getString(R.string.option_6_1), () -> startSituation(9)),
                                new Option(getString(R.string.option_6_2), () -> startEnding(5)),
                                new Option(getString(R.string.option_6_3), () -> startEnding(6))
                        }
                ),
                new Situation( // 7
                        getString(R.string.story_7),
                        new Option[]{
                                new Option(getString(R.string.option_7_1), () -> startSituation(9)),
                                new Option(getString(R.string.option_7_2), () -> startEnding(7)),
                                new Option(getString(R.string.option_7_3), () -> startSituation(10))
                        }
                ),
                new Situation( // 8
                        getString(R.string.story_8),
                        new Option[]{
                                new Option(getString(R.string.option_8_1), () -> startSituation(11)),
                                new Option(getString(R.string.option_8_2), () -> startEnding(8)),
                                new Option(getString(R.string.option_8_3), () -> startEnding(9))
                        }
                ),
                new Situation( // 9
                        getString(R.string.story_9),
                        new Option[]{
                                new Option(getString(R.string.option_9_1), () -> startEnding(14)),
                                new Option(getString(R.string.option_9_2), () -> startEnding(10)),
                                new Option(getString(R.string.option_9_3), () -> startEnding(11))
                        }
                ),
                new Situation( // 10
                        getString(R.string.story_10),
                        new Option[]{
                                new Option(getString(R.string.option_10_1), () -> startEnding(14)),
                                new Option(getString(R.string.option_10_2), () -> startEnding(12)),
                                new Option(getString(R.string.option_10_3), () -> startEnding(13))
                        }
                ),
                new Situation( // 11
                        getString(R.string.story_11),
                        new Option[]{
                                new Option(getString(R.string.option_11_1), () -> startEnding(14)),
                                new Option(getString(R.string.option_11_2), () -> startEnding(15)),
                                new Option(getString(R.string.option_11_3), () -> startEnding(16))
                        }
                ),
        };


        endings = new String[]{
                getString(R.string.ending_0),
                getString(R.string.ending_1),
                getString(R.string.ending_2),
                getString(R.string.ending_3),
                getString(R.string.ending_4),
                getString(R.string.ending_5),
                getString(R.string.ending_6),
                getString(R.string.ending_7),
                getString(R.string.ending_8),
                getString(R.string.ending_9),
                getString(R.string.ending_10),
                getString(R.string.ending_11),
                getString(R.string.ending_12),
                getString(R.string.ending_13),
                getString(R.string.ending_14),
                getString(R.string.ending_15),
                getString(R.string.ending_16)
        };

    }

    private void startEnding(int id) {
        storyText.setText(endings[id]);
        optionButton1.setText(getString(R.string.again_btn));
        optionButton2.setText("");
        optionButton3.setText("");
        optionButton2.setEnabled(false);
        optionButton3.setEnabled(false);
        
        optionButton1.setOnClickListener(view -> {
            optionButton1.setOnClickListener(_view -> handleOption(0));
            optionButton2.setEnabled(true);
            optionButton3.setEnabled(true);
            startSituation(0);
        });
    }

    private void startSituation(int id) {
        storyText.setText(situations[id].story);
        optionButton1.setText(situations[id].options[0].text);
        optionButton2.setText(situations[id].options[1].text);
        optionButton3.setText(situations[id].options[2].text);
        situationId = id;
    }

    private void handleOption(int optionId) {
        situations[situationId].options[optionId].onPick.run();
        Log.i("option", String.valueOf(optionId));
    }
}