package fr.iut.vanbossm.bluetoothrobotcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import fr.iut.vanbossm.BTConnectionHandler;
import fr.iut.vanbossm.exceptions.BTHandlingException;

/**
 * Created by Maxime-Portable on 17/03/2016.
 */
public class ControllerActivity extends ActionBarActivity {

    private BTConnectionHandler btConnectionHandler;
    private String status;

    public void onCreate(Bundle savedInstanceState) {
        this.status = "Disconnected";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        ((TextView) findViewById(R.id.statusTextView)).setText(this.status);
        btConnectionHandler = new BTConnectionHandler(this);
    }

    public void mainActivityClickListener(View view) {
        switch (view.getId()) {
            case R.id.ConnectingButton:
                connect(view);
                break;
            case R.id.DisconnectingButton:
                disconnect(view);
                break;
            case R.id.SendingButton:
                send(view);
                break;
            case R.id.FrontButton:
                front();
                break;
            case R.id.LeftButton:
                left();
                break;
            case R.id.StopButton:
                stop();
                break;
            case R.id.RightButton:
                right();
                break;
            case R.id.BackButton:
                back();
                break;
            case R.id.JoystickButton:
                joystick();
                break;
            default:
        }
    }

    public void connect(View connectView) {
            EditText deviceName = (EditText) findViewById(R.id.MakeblockEditText);
            try {
                btConnectionHandler.connectToBTDevice(deviceName.getText().toString());
                ((TextView) findViewById(R.id.statusTextView)).setText("Connected");
            } catch (BTHandlingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void disconnect(View disconnectView) {

            this.btConnectionHandler.closeConnection();

            ((TextView) findViewById(R.id.statusTextView)).setText("Disconnected");

    }

    public void send(View sendView) {
        EditText commandEntry = (EditText) findViewById(R.id.CommandEditText);
        try {
            btConnectionHandler.sendData(commandEntry.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BTHandlingException e) {
            e.printStackTrace();
        }
    }

    public void front() {
        try {
            btConnectionHandler.sendData("A");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BTHandlingException e) {
            e.printStackTrace();
        }
    }

    public void left() {
        try {
            btConnectionHandler.sendData("G");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BTHandlingException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            btConnectionHandler.sendData("S");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BTHandlingException e) {
            e.printStackTrace();
        }
    }

    public void right() {
        try {
            btConnectionHandler.sendData("D");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BTHandlingException e) {
            e.printStackTrace();
        }
    }

    public void back() {
        try {
            btConnectionHandler.sendData("R");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BTHandlingException e) {
            e.printStackTrace();
        }
    }

    public void joystick() {
        JoystickView joystickView = new JoystickView(this);
        setContentView(joystickView);
        JoystickView.ValueChangedHandler handler = new JoystickView.ValueChangedHandler() {
            @Override
            public void onValueChanged(int Vg, int Vd) {
                if (Vg == 0 && Vd == 0) {
                    stop();
                }
                if ((Vd - Vg < 10 || Vg - Vd < 10) && Vd > 0 && Vg > 0) {
                    front();
                }
                if ((Vd - Vg < 10 || Vg - Vd < 10) && Vd < 0 && Vg < 0) {
                    back();
                }
                if (Vd > Vg && Vd - Vg > 10) {
                    left();
                }
                if (Vd < Vg && Vg - Vd > 10) {
                    right();
                }
            }
        };
        joystickView.setValueChangeHandler(handler);
    }
}