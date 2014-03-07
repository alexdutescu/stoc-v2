package ro.alexdutescu.coduribare.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Scope;

import ro.alexdutescu.coduribare.model.StocTotal;
import ro.alexdutescu.coduribare.model.Utilizator;
import ro.alexdutescu.coduribare.service.StocTotalService;
import ro.alexdutescu.coduribare.utils.LazyStocDataModel;

@Named("page")
@Scope("session")
@SuppressWarnings("serial")
public class PageBean implements Serializable{
    
    private String inputLocatie, inputNume, inputProducator;
    private String outputNume, outputProducator;
    private Integer inputCod, inputCantFlacon;
    private Integer outputCod, outputCantFlacon;
    private boolean selectat;

    private LazyDataModel<StocTotal> lazyStocModel;
    private StocTotal selectedRow;
    private List<StocTotal> allStoc;
    private DataTable dt;
    private Column colNume;

    public String getOutputNume() {
        return outputNume;
    }

    public void setOutputNume(String outputNume) {
        this.outputNume = outputNume;
    }

    public String getOutputProducator() {
        return outputProducator;
    }

    public void setOutputProducator(String outputProducator) {
        this.outputProducator = outputProducator;
    }

    public Integer getOutputCod() {
        return outputCod;
    }

    public void setOutputCod(Integer outputCod) {
        this.outputCod = outputCod;
    }

    public Integer getOutputCantFlacon() {
        return outputCantFlacon;
    }

    public void setOutputCantFlacon(Integer outputCantFlacon) {
        this.outputCantFlacon = outputCantFlacon;
    }

    public Column getColNume() {
        return colNume;
    }

    public void setColNume(Column colNume) {
        this.colNume = colNume;
    }

    public DataTable getDt() {
        return dt;
    }

    public void setDt(DataTable dt) {
        this.dt = dt;
    }

    public Integer getInputCantFlacon() {
        return inputCantFlacon;
    }

    public void setInputCantFlacon(Integer inputCantFlacon) {
        this.inputCantFlacon = inputCantFlacon;
    }

    public LazyDataModel<StocTotal> getLazyStocModel() {
        return lazyStocModel;
    }

    public void setLazyStocModel(LazyDataModel<StocTotal> lazyStocModel) {
        this.lazyStocModel = lazyStocModel;
    }

    public StocTotal getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(StocTotal selectedRow) {
        this.selectedRow = selectedRow;
    }

    public List<StocTotal> getAllStoc() {
        return allStoc;
    }

    public void setAllStoc(List<StocTotal> allStoc) {
        this.allStoc = allStoc;
    }
    
    public boolean isSelectat() {
        return selectat;
    }

    public void setSelectat(boolean selectat) {
        this.selectat = selectat;
    }
    
    public String getInputLocatie() {
        return inputLocatie;
    }

    public void setInputLocatie(String inputLocatie) {
        this.inputLocatie = inputLocatie;
    }

    public String getInputNume() {
        return inputNume;
    }

    public void setInputNume(String inputNume) {
        this.inputNume = inputNume;
    }

    public String getInputProducator() {
        return inputProducator;
    }

    public void setInputProducator(String inputProducator) {
        this.inputProducator = inputProducator;
    }

    public Integer getInputCod() {
        return inputCod;
    }

    public void setInputCod(Integer inputCod) {
        this.inputCod = inputCod;
    }
//    End getters & setters
    @Inject StocTotalService stocService;
    
    public PageBean() {
        
    }
    
    @PostConstruct
    public void init() {
    	Utilizator u = (Utilizator)Faces.getSessionAttribute("utilizator");
        colNume = new Column();
        if (u != null && u.getRol().getDenumire().toLowerCase().equals("administrator")) {
        	lazyStocModel = new LazyStocDataModel(stocService.findAll());
        }
        if (u != null && u.getLocatie().toLowerCase().equals("angajat")) {
        	lazyStocModel = new LazyStocDataModel(stocService.findStocByFilters(u.getLocatie(), null, null, null, true));
        }
        selectat = true;
    }
    
    public void onRowSelect(SelectEvent event) {
//        FacesMessage msg = new FacesMessage("Rand selectat", ((StocTotal) event.getObject()).getNume());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        StocTotal st = (StocTotal)event.getObject();
        debugMessage("Selectie: "+ st.getNume());
        outputNume = st.getNume();
        outputCantFlacon = st.getCantFlacon();
        outputCod = st.getCod();
        outputProducator = st.getProducator();
    }

    public void onKeyUp() {
        debugMessage(dt.getFilterEvent());
        debugMessage(dt.getFilteredValue().toString());
        debugMessage((dt.getFilters().toString()));
    }
    
    public void manageApplyButton() {
        debugMessage("Pas Apply");
        debugMessage("Valoare input nume: "+ inputNume);
        Utilizator u = (Utilizator)Faces.getSessionAttribute("utilizator");
        if(u != null && u.getLocatie().toLowerCase().equals("administrator")) {
        	debugMessage("Pas GOD");
            lazyStocModel = new LazyStocDataModel(stocService.findStocByFilters(inputLocatie, inputCod, inputNume, inputProducator, selectat));
        }
        if(u != null && u.getLocatie().toLowerCase().equals("angajat")) {
        	debugMessage("Pas NOT GOD");
        	List<StocTotal> list = stocService.findStocByFilters(u.getLocatie(), inputCod, inputNume, inputProducator, selectat);
        	debugMessage("Marime lista "+ list.size());
            lazyStocModel = new LazyStocDataModel(list);
        }
        
        debugMessage("Iesire pas");
    }
    
    private void debugMessage(String mesaj) {
        Logger.getLogger(PageBean.class.toString()).log(Level.INFO, mesaj);
    }
}
