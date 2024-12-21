package dev.chsr.thestory;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

class Situation {
    String story;
    String[] options;
    Runnable[] onChoices;
    boolean isAnswered = false;

    public Situation(String story, String[] options, Runnable[] onChoices) {
        this.story = story;
        this.options = options;
        this.onChoices = onChoices;
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
                        new String[]{
                                getString(R.string.option_0_1),
                                getString(R.string.option_0_2),
                                getString(R.string.option_0_3)
                        },
                        new Runnable[]{
                                () -> startSituation(1),
                                () -> startSituation(2),
                                () -> startEnding(0)
                        }
                ),
                new Situation( // 1
                        getString(R.string.story_1),
                        new String[]{
                                getString(R.string.option_1_1),
                                getString(R.string.option_1_2),
                                getString(R.string.option_1_3)
                        },
                        new Runnable[]{
                                () -> startSituation(3),
                                () -> startSituation(4),
                                () -> startEnding(3)
                        }
                ),
                new Situation( // 2
                        getString(R.string.story_2),
                        new String[]{
                                getString(R.string.option_2_1),
                                getString(R.string.option_2_2),
                                getString(R.string.option_2_3)
                        },
                        new Runnable[]{
                                () -> startSituation(1),
                                () -> startEnding(4),
                                () -> startEnding(0)
                        }
                ),
                new Situation( // 3
                        getString(R.string.story_3),
                        new String[]{
                                getString(R.string.option_3_1),
                                getString(R.string.option_3_2),
                                getString(R.string.option_3_3)
                        },
                        new Runnable[]{
                                () -> startSituation(4),
                                () -> startSituation(5),
                                () -> startSituation(5)
                        }
                ),
                new Situation( // 4
                        getString(R.string.story_4),
                        new String[]{
                                getString(R.string.option_4_1),
                                getString(R.string.option_4_2),
                                getString(R.string.option_4_3)
                        },
                        new Runnable[]{
                                () -> startSituation(5),
                                () -> startEnding(2),
                                () -> startSituation(5)
                        }
                ),
                new Situation( // 5
                        getString(R.string.story_5),
                        new String[]{
                                getString(R.string.option_5_1),
                                getString(R.string.option_5_2),
                                getString(R.string.option_5_3)
                        },
                        new Runnable[]{
                                () -> startSituation(6),
                                () -> startSituation(7),
                                () -> startSituation(8)
                        }
                ),
                new Situation( // 6
                        getString(R.string.story_6),
                        new String[]{
                                getString(R.string.option_6_1),
                                getString(R.string.option_6_2),
                                getString(R.string.option_6_3)
                        },
                        new Runnable[]{
                                () -> startSituation(9),
                                () -> startEnding(5),
                                () -> startEnding(6)
                        }
                ),
                new Situation( // 7
                        getString(R.string.story_7),
                        new String[]{
                                getString(R.string.option_7_1),
                                getString(R.string.option_7_2),
                                getString(R.string.option_7_3)
                        },
                        new Runnable[]{
                                () -> startSituation(9),
                                () -> startEnding(7),
                                () -> startSituation(10)
                        }
                ),
                new Situation( // 8
                        getString(R.string.story_8),
                        new String[]{
                                getString(R.string.option_8_1),
                                getString(R.string.option_8_2),
                                getString(R.string.option_8_3)
                        },
                        new Runnable[]{
                                () -> startSituation(11),
                                () -> startEnding(8),
                                () -> startEnding(9)
                        }
                ),
                new Situation( // 9
                        getString(R.string.story_9),
                        new String[]{
                                getString(R.string.option_9_1),
                                getString(R.string.option_9_2),
                                getString(R.string.option_9_3)
                        },
                        new Runnable[]{
                                () -> startEnding(14),
                                () -> startEnding(10),
                                () -> startEnding(11)
                        }
                ),
                new Situation( // 10
                        getString(R.string.story_10),
                        new String[]{
                                getString(R.string.option_10_1),
                                getString(R.string.option_10_2),
                                getString(R.string.option_10_3)
                        },
                        new Runnable[]{
                                () -> startEnding(14),
                                () -> startEnding(12),
                                () -> startEnding(13)
                        }
                ),
                new Situation( // 11
                        getString(R.string.story_11),
                        new String[]{
                                getString(R.string.option_11_1),
                                getString(R.string.option_11_2),
                                getString(R.string.option_11_3)
                        },
                        new Runnable[]{
                                () -> startEnding(14),
                                () -> startEnding(15),
                                () -> startEnding(16)
                        }
                ),
        };

        endings = new String[] {
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
        optionButton1.setText(situations[id].options[0]);
        optionButton2.setText(situations[id].options[1]);
        optionButton3.setText(situations[id].options[2]);
        situationId = id;
    }

    private void handleOption(int optionId) {
        situations[situationId].onChoices[optionId].run();
        Log.i("option", String.valueOf(optionId));
    }
}