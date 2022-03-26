package com.auth.koperasi.service.dao.query;

public interface QueryTransaksi {

    String QUERY_SALDO_NASABAH = "select nip, namaNasabah, sum(saldoWajib) as saldoWajib, 100000 as simpananPokok,\n" +
            "sum(saldoSukaRela) as saldoSukaRela, sum(transaksiWajib) as trWajib, \n" +
            "sum(transaksiSukaRela) as trSukaRela, sum(saldoWajib+saldoSukaRela)+100000 as saldoAkhir\n" +
            "from (\n" +
            "\tselect n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\tsum(tt.\"NOMINAL_TRANSAKSI\") as saldoWajib,\n" +
            "\t0 as saldoSukaRela,\n" +
            "\t0 as transaksiWajib,\n" +
            "\t0 as transaksiSukaRela\n" +
            "\tfrom \"TN_TRANSAKSI\" tt join \"NASABAH\" n on tt.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
            "\twhere tt.\"JENIS_TRANSAKSI\" = 'SIMPANAN WAJIB'\n" +
            "\tgroup by nip, namaNasabah\n" +
            "\tunion all \n" +
            "\tselect n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as saldoWajib,\n" +
            "\tsum(tt.\"NOMINAL_TRANSAKSI\") as saldoSukaRela,\n" +
            "\t0 as transaksiWajib,\n" +
            "\t0 as transaksiSukaRela\n" +
            "\tfrom \"TN_TRANSAKSI\" tt join \"NASABAH\" n on tt.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
            "\twhere tt.\"JENIS_TRANSAKSI\" = 'SIMPANAN SUKA RELA'\n" +
            "\tgroup by nip, namaNasabah\n" +
            "\tunion all \n" +
            "\tselect n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as saldoWajib,\n" +
            "\t0 as saldoSukaRela,\n" +
            "\tcount(*) as transaksiWajib,\n" +
            "\t0 as transaksiSukaRela\n" +
            "\tfrom \"TN_TRANSAKSI\" tt join \"NASABAH\" n on tt.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
            "\twhere tt.\"JENIS_TRANSAKSI\" = 'SIMPANAN WAJIB'\n" +
            "\tgroup by nip, namaNasabah\n" +
            "\tunion all \n" +
            "\tselect n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as saldoWajib,\n" +
            "\t0 as saldoSukaRela,\n" +
            "\t0 as transaksiWajib,\n" +
            "\tcount(*) as transaksiSukaRela\n" +
            "\tfrom \"TN_TRANSAKSI\" tt join \"NASABAH\" n on tt.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
            "\twhere tt.\"JENIS_TRANSAKSI\" = 'SIMPANAN SUKA RELA'\n" +
            "\tgroup by nip, namaNasabah\n" +
            ")as datas where nip = :nip\n" +
            "group by nip, namaNasabah";
}
