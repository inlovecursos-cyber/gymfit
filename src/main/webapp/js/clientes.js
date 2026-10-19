const API_URL = "/ginasio/api/clientes";
const AULAS_URL = "/ginasio/api/aulas";


// ==========================================
// CLIENTES
// ==========================================

async function carregarClientes() {

    const lista = document.getElementById("listaClientes");

    try {

        const resposta = await fetch(API_URL);

        if (!resposta.ok) {
            throw new Error("Erro ao carregar clientes");
        }

        const clientes = await resposta.json();

        lista.innerHTML = "";

        if (clientes.length === 0) {
            lista.innerHTML = "<p>Não existem clientes.</p>";
            return;
        }

        clientes.forEach(cliente => {

            const div = document.createElement("div");

            div.className = "cliente";

            div.innerHTML = `
                <h3>${cliente.nome}</h3>

                <p>
                    <strong>Email:</strong>
                    ${cliente.email}
                </p>

                <p>
                    <strong>Telefone:</strong>
                    ${cliente.telefone}
                </p>

                <p>
                    <strong>Objetivo:</strong>
                    ${cliente.objetivo}
                </p>

                <button onclick="editarCliente(${cliente.id})">
                    ✏️ Editar
                </button>

                <button onclick="apagarCliente(${cliente.id})">
                    🗑️ Apagar
                </button>
            `;

            lista.appendChild(div);
        });

    } catch (erro) {

        console.error(erro);

        lista.innerHTML =
            "<p>Erro ao carregar os clientes.</p>";
    }
}


// ==========================================
// ADICIONAR CLIENTE
// ==========================================

document
    .getElementById("clienteForm")
    .addEventListener("submit", async function(event) {

        event.preventDefault();

        const nome =
            document.getElementById("nome").value;

        const email =
            document.getElementById("email").value;

        const telefone =
            document.getElementById("telefone").value;

        const objetivo =
            document.getElementById("objetivo").value;


        const cliente = {

            nome: nome,
            email: email,
            telefone: telefone,
            objetivo: objetivo
        };


        try {

            const resposta = await fetch(API_URL, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(cliente)
            });


            if (!resposta.ok) {
                throw new Error("Erro ao adicionar cliente");
            }


            alert("Cliente adicionado com sucesso!");


            document
                .getElementById("clienteForm")
                .reset();


            carregarClientes();


        } catch (erro) {

            console.error(erro);

            alert("Não foi possível adicionar o cliente.");
        }

    });


// ==========================================
// EDITAR CLIENTE
// ==========================================

async function editarCliente(id) {

    try {

        const resposta = await fetch(`${API_URL}/${id}`);

        if (!resposta.ok) {
            throw new Error("Cliente não encontrado");
        }

        const cliente = await resposta.json();


        const nome = prompt(
            "Nome do cliente:",
            cliente.nome
        );

        if (nome === null) {
            return;
        }


        const email = prompt(
            "Email:",
            cliente.email
        );

        if (email === null) {
            return;
        }


        const telefone = prompt(
            "Telefone:",
            cliente.telefone
        );

        if (telefone === null) {
            return;
        }


        const objetivo = prompt(
            "Objetivo:",
            cliente.objetivo
        );

        if (objetivo === null) {
            return;
        }


        const clienteAtualizado = {

            nome: nome,
            email: email,
            telefone: telefone,
            objetivo: objetivo
        };


        const respostaAtualizacao = await fetch(
            `${API_URL}/${id}`,
            {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(clienteAtualizado)
            }
        );


        if (!respostaAtualizacao.ok) {
            throw new Error("Erro ao atualizar cliente");
        }


        alert("Cliente atualizado com sucesso!");


        carregarClientes();


    } catch (erro) {

        console.error(erro);

        alert("Não foi possível editar o cliente.");
    }
}


// ==========================================
// APAGAR CLIENTE
// ==========================================

async function apagarCliente(id) {

    const confirmar = confirm(
        "Tem a certeza que quer apagar este cliente?"
    );


    if (!confirmar) {
        return;
    }


    try {

        const resposta = await fetch(
            `${API_URL}/${id}`,
            {
                method: "DELETE"
            }
        );


        if (!resposta.ok) {
            throw new Error("Erro ao apagar cliente");
        }


        alert("Cliente apagado com sucesso!");


        carregarClientes();


    } catch (erro) {

        console.error(erro);

        alert("Não foi possível apagar o cliente.");
    }
}


// ==========================================
// AULAS
// ==========================================

async function carregarAulas() {

    const lista =
        document.getElementById("listaAulas");


    try {

        const resposta =
            await fetch(AULAS_URL);


        if (!resposta.ok) {

            throw new Error(
                "Erro ao carregar aulas"
            );
        }


        const aulas =
            await resposta.json();


        lista.innerHTML = "";


        if (aulas.length === 0) {

            lista.innerHTML =
                "<p>Não existem aulas hoje.</p>";

            return;
        }


        aulas.forEach(aula => {

            const div =
                document.createElement("div");


            div.className = "aula";


            div.innerHTML = `

                <h3>
                    🏋️ ${aula.nome}
                </h3>

                <p>
                    <strong>🕐 Horário:</strong>
                    ${aula.horario}
                </p>

                <p>
                    <strong>👨‍🏫 Instrutor:</strong>
                    ${aula.instrutor}
                </p>

                <p>
                    <strong>👥 Capacidade:</strong>
                    ${aula.capacidade} pessoas
                </p>

                <button
                    onclick="agendarAula(
                        ${aula.id},
                        '${aula.nome}',
                        '${aula.horario}'
                    )"
                >
                    📅 Agendar
                </button>

            `;


            lista.appendChild(div);

        });


    } catch (erro) {

        console.error(erro);


        lista.innerHTML =
            "<p>Erro ao carregar as aulas.</p>";
    }
}


// ==========================================
// AGENDAR AULA
// ==========================================

function agendarAula(
    id,
    nome,
    horario
) {

    const cliente =
        prompt(
            `Agendar ${nome} às ${horario}\n\n` +
            "Digite o nome do cliente:"
        );


    if (!cliente) {
        return;
    }


    alert(
        `Agendamento realizado!\n\n` +
        `Cliente: ${cliente}\n` +
        `Aula: ${nome}\n` +
        `Horário: ${horario}`
    );
}


// ==========================================
// INICIAR PÁGINA
// ==========================================

carregarClientes();

carregarAulas();