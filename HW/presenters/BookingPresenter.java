package HW.presenters;

import java.util.Collection;
import java.util.Date;

import HW.models.Table;

public class BookingPresenter implements ViewObserver {
    private final Model model;
    private final View view;

    public BookingPresenter(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setObserver(this);
    }

   
    public Collection<Table> loadTables() {
        return model.loadTables();
    }

    public void updateUIShowTables() {
        view.showTables(loadTables());
    }

    public void updateUIShowReservationTableResult(int reservationNumber) {
        view.showReservationTableResult(reservationNumber);
    }

    public void updateUIShowshowСhangeReservationTable(int reservationNumber, int numberTable) {
        view.showСhangeReservationTable(reservationNumber, numberTable);
    }

    @Override
    public void onReservetionTable(Date orderDate, int tableNumber, String nameClients) {
        try {
            int reservationNumber = model.reservationTable(orderDate, tableNumber, nameClients);
            updateUIShowReservationTableResult(reservationNumber);
        } catch (RuntimeException e) {
            updateUIShowReservationTableResult(-1);
        }

    }

    @Override
    public void onUpdateReservetionTable(int oldReservation, Date reservationDate, int tableNumber,
            String nameClients) {
        try {
            int newReservationNumber = model.changeReservationTable(oldReservation, reservationDate, tableNumber,
                    nameClients);
            updateUIShowshowСhangeReservationTable(newReservationNumber, tableNumber);
        } catch (RuntimeException e) {
            updateUIShowshowСhangeReservationTable(-1, -1);
        }
    }
}
