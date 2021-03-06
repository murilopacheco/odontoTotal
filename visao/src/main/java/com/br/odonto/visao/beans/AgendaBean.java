package com.br.odonto.visao.beans;

import com.br.odonto.odontoTotal.controller.ClienteController;
import com.br.odonto.odontoTotal.controller.ProcedimentoController;
import com.br.odonto.odontoTotal.controller.ProfissionalController;
import com.br.odonto.odontoTotal.dominio.Agendamento;
import com.br.odonto.odontoTotal.dominio.Cliente;
import com.br.odonto.odontoTotal.dominio.Procedimento;
import com.br.odonto.odontoTotal.dominio.Profissional;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;

@Named(value = "agendaBean")
@SessionScoped
public class AgendaBean implements Serializable {

    private ScheduleModel eventModel;
    private Cliente cliente = new Cliente();
    private Procedimento procedimento = new Procedimento();
    private Profissional profissional = new Profissional();
    private List<Profissional> profissionais = new ArrayList<Profissional>();
    private Agendamento agendamento;




    private ScheduleEvent event = new DefaultScheduleEvent();

    @PostConstruct
    public void init() {

        eventModel = new DefaultScheduleModel();
        DefaultScheduleEvent ev = new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm());
        ev.setStyleClass(" confirmado");
        eventModel.addEvent(ev);

    listarProfissionais();
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }


    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public List<Cliente> listaClientes(String cliente){
        ClienteController con = new ClienteController();
        return  con.pesquisarPorCliente(cliente);

    }

    public List<Procedimento> listaProcedimentos(String procedimento){
        ProcedimentoController pro = new ProcedimentoController();
        return pro.pesquisarPorProcedimento(procedimento);

    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null) {
            agendamento = new Agendamento();
            agendamento.setHoraInicio(convertDate(event.getStartDate()));
            agendamento.setHoraInicio(agendamento.getHoraInicio().minusHours(1));
            agendamento.setHoraFim(convertDate(event.getEndDate()));
            agendamento.setHoraFim(agendamento.getHoraFim().minusHours(1));
            agendamento.setProcedimento(procedimento);
            agendamento.setCliente(cliente);
            agendamento.setProfissional(profissional);
            agendamento.setTitulo("Paciente: " + cliente.getNome() + " " + LocalTime.of(agendamento.getHoraInicio().getHour(),agendamento.getHoraInicio().getMinute()) + " - "
                    + LocalTime.of(agendamento.getHoraFim().getHour(),agendamento.getHoraFim().getMinute()));

            eventModel.addEvent(new DefaultScheduleEvent(agendamento.getTitulo(), convertDate(agendamento.getHoraInicio()), convertDate(agendamento.getHoraFim())));
        } else {
            eventModel.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
        cliente = new Cliente();
        procedimento = new Procedimento();
        profissional = new Profissional();
    }

//    public void addEvent(ActionEvent actionEvent) {
//        if(event.getId() == null)
//            eventModel.addEvent(event);
//        else
//            eventModel.updateEvent(event);
//
//        event = new DefaultScheduleEvent();
//    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void listarProfissionais(){
        ProfissionalController con = new ProfissionalController();
        profissionais = con.consultar();
    }

    public LocalDateTime convertDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of( "America/Sao_Paulo" );
        return  instant.atZone(zoneId).toLocalDateTime();
    }

    public Date convertDate(LocalDateTime date){
        ZoneId zoneId = ZoneId.of( "America/Sao_Paulo" );
        return  Date.from(date.atZone(zoneId).toInstant());
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}