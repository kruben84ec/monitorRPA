package org.util;

import java.sql.Time;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.NetworkIF;

import java.util.List;

public class HealthBeat{
    private Boolean status;
    private SystemInfo si = new SystemInfo();
    private CentralProcessor processor = si.getHardware().getProcessor();
    private long[] prevTicks;
    private long[] currTicks;

    public HealthBeat() {
        this.setStatus(false);
        this.prevTicks = processor.getSystemCpuLoadTicks();
    }
    public String getNombreBot() {
        return nombreBot;
    }

    public void setNombreBot(String nombreBot) {
        this.nombreBot = nombreBot;
    }

    private String nombreBot;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



    public Time initHealthBeat() {
        return new Time(System.currentTimeMillis());
    }

    // Métodos para obtener información del sistema usando OSHI
    public String getUsoDisco() {
        List<HWDiskStore> discos = si.getHardware().getDiskStores();
        StringBuilder sb = new StringBuilder();
        for (HWDiskStore disco : discos) {
            disco.updateAttributes();  // Actualiza la información del disco
            long total = disco.getSize();
            long libre = disco.getSize() - (disco.getReadBytes() + disco.getWriteBytes());
            long usado = total - libre;

            sb.append("Disco: ").append(disco.getName())
              .append(", Total: ").append(total / (1024 * 1024 * 1024)).append(" GB")
              .append(", Usado: ").append(usado / (1024 * 1024 * 1024)).append(" GB")
              .append(", Libre: ").append(libre / (1024 * 1024 * 1024)).append(" GB")
              .append(", Lecturas: ").append(disco.getReads())
              .append(", Escrituras: ").append(disco.getWrites())
              .append("\n");
        }
        return sb.toString();
    }

    public String getUsoMemoria() {
        GlobalMemory memoria = si.getHardware().getMemory();
        long total = memoria.getTotal();
        long disponible = memoria.getAvailable();
        long usado = total - disponible;

        return String.format("Memoria - Total: %.2f GB, Usado: %.2f GB, Libre: %.2f GB, Porcentaje usado: %.2f%%",
                total / 1e9,
                usado / 1e9,
                disponible / 1e9,
                ((double) usado / total) * 100);
    }

    public String getUsoCPU() {
        currTicks = processor.getSystemCpuLoadTicks();
        double cpuUso = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        prevTicks = currTicks;

        return String.format("CPU - Uso: %.2f%%, Núcleos físicos: %d, Núcleos lógicos: %d, " +
                        "Frecuencia: %.2f GHz",
                cpuUso,
                processor.getPhysicalProcessorCount(),
                processor.getLogicalProcessorCount(),
                processor.getMaxFreq() / 1e9);
    }

    public String getUsoRed() {
        SystemInfo si = new SystemInfo();
        List<NetworkIF> redes = si.getHardware().getNetworkIFs();
        StringBuilder sb = new StringBuilder();
        for (NetworkIF net : redes) {
            sb.append("Interfaz: ").append(net.getName())
              .append(", Bytes enviados: ").append(net.getBytesSent())
              .append(", Bytes recibidos: ").append(net.getBytesRecv())
              .append("\n");
        }
        return sb.toString();
    }

}
