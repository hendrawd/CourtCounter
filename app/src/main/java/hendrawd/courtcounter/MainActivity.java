package hendrawd.courtcounter;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvGameTitle)
    TextView tvGameTitle;
    @BindView(R.id.vVerticalLine)
    View vVerticalLine;
    @BindView(R.id.tvTeamAName)
    TextView tvTeamAName;
    @BindView(R.id.tvTeamAScore)
    TextView tvTeamAScore;
    @BindView(R.id.bTeamAPlus3)
    Button bTeamAPlus3;
    @BindView(R.id.bTeamAPlus1)
    Button bTeamAPlus1;
    @BindView(R.id.bTeamAFreeThrow)
    Button bTeamAFreeThrow;
    @BindView(R.id.tvTeamBName)
    TextView tvTeamBName;
    @BindView(R.id.tvTeamBScore)
    TextView tvTeamBScore;
    @BindView(R.id.bTeamBPlus3)
    Button bTeamBPlus3;
    @BindView(R.id.bTeamBPlus1)
    Button bTeamBPlus1;
    @BindView(R.id.bTeamBFreeThrow)
    Button bTeamBFreeThrow;
    private GameScoreViewModel gameScoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gameScoreViewModel = ViewModelProviders.of(this).get(GameScoreViewModel.class);

        // subscribe to observables
        gameScoreViewModel.getScoreTeamA().observe(this, score -> tvTeamAScore.setText(score));
        gameScoreViewModel.getScoreTeamB().observe(this, score -> tvTeamBScore.setText(score));
        gameScoreViewModel.getGameTitle().observe(this, gameTitle -> tvGameTitle.setText(gameTitle));
        gameScoreViewModel.getTeamAName().observe(this, name -> tvTeamAName.setText(name));
        gameScoreViewModel.getTeamBName().observe(this, name -> tvTeamBName.setText(name));

        // set team names and game title
        gameScoreViewModel.setGameTitle("August 18 Playoff");
        gameScoreViewModel.setTeamAName("Cupcake");
        gameScoreViewModel.setTeamBName("Donut");
    }

    @OnClick({
            R.id.bTeamAPlus3,
            R.id.bTeamAPlus1,
            R.id.bTeamAFreeThrow,
            R.id.bTeamBPlus3,
            R.id.bTeamBPlus1,
            R.id.bTeamBFreeThrow,
            R.id.bReset
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bTeamAPlus3:
                gameScoreViewModel.addToTeamA(3);
                break;
            case R.id.bTeamAPlus1:
                gameScoreViewModel.addToTeamA(1);
                break;
            case R.id.bTeamAFreeThrow:
                showToast("I don't know the rule, dude");
                break;
            case R.id.bTeamBPlus3:
                gameScoreViewModel.addToTeamB(3);
                break;
            case R.id.bTeamBPlus1:
                gameScoreViewModel.addToTeamB(1);
                break;
            case R.id.bTeamBFreeThrow:
                showToast("I don't know the rule, dude");
                break;
            case R.id.bReset:
                gameScoreViewModel.resetScores();
                break;
        }
    }

    private void showToast(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
