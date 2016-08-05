package infosw.agenda.com.agendamedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendar;
    private TextView header;
    private Menu menu;

    private int globalyear;
    private int globalmonth;
    private int globaldayOfMonth;
    private boolean hideIcon = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //getSupportActionBar por problemas de compatibilidad
        getSupportActionBar().setTitle(R.string.new_date);

        calendar = (CalendarView) findViewById(R.id.calendarView);
        header = (TextView) findViewById(R.id.select_id);

        if (calendar != null) {
            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                   globalyear = year;
                   globalmonth = month;
                   globaldayOfMonth = dayOfMonth;
                   hideIcon = false;
                   supportInvalidateOptionsMenu(); //Hace aparecer el boton next cuando hay una fecha valida
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        if (hideIcon){
            menu.findItem(R.id.save_selected_date).setVisible(false);
        }else{
            menu.findItem(R.id.save_selected_date).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.save_selected_date:
                selectPatientDate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void selectPatientDate(){
        Intent datesView = new Intent(this, DatesActivity.class);
        datesView.putExtra("year", globalyear);
        datesView.putExtra("month", globalmonth);
        datesView.putExtra("dayOfMonth", globaldayOfMonth);
        startActivity(datesView);
    }

    @Override
       public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);

    }
}
